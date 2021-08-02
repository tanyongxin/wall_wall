package wall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.ReportMapper;
import wall.entity.Pageable;
import wall.pojo.JudgeVo;
import wall.pojo.ReportSub;
import wall.pojo.ReportVo;
import wall.service.*;
import wall.util.IdWorker;
import wall.util.Res;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService, InitializingBean {


    @Autowired
    private UserServiceRpc userServiceRpc;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ReportSubService reportSubService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private LoveService loveService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private LikeService likeService;

    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private Thread thread = new Thread(() -> {
        long userId = 0L,originalUserId = userId;
        long reportSubId = 0L,originalReportSubId = reportSubId;
        long reportVoId = 0L,originalReportVoId = reportVoId;
        long loveParentId = 0L,questionId = 0L,replyId = 0L;
        int pageSize = 10;
        while (true){
            ReportVo currReportVo = null;
            boolean alloc = false;
            try {
                Thread.sleep(1800000); // 每 30 分钟调度一次
                // todo：1、分配审核任务，从数据库取出举报列表进行分配
                logger.info("start alloc audit task");
                long currTimeTimeMillis = System.currentTimeMillis();
                originalReportVoId = reportVoId;
                List<ReportVo> reportVos = reportService.selectReportVosByRange(reportVoId,pageSize);
                for (ReportVo vo : reportVos) {
                    currReportVo = vo;
                    reportVoId = Math.max(reportVoId,vo.getId());
                    if (vo.getAuditRes() != 0) // 该举报已完成审核，继续下轮循环
                        continue;
                    // 查询当前举报对象 vo 对应的审核结果
                    List<ReportSub> reportSubs = reportSubService.selectByParentId(vo.getId());
                    int agree = 0; // 同意封贴的数量
                    int disagree = 0; // 不同意封贴
                    int auditRealSize = 0; // 实际审核人数
                    if( reportSubs != null && reportSubs.size() > 0 ){
                        for (ReportSub reportSub : reportSubs) {
                            // 统计实际的审核人数
                            if (reportSub.getIsValid() != 0 && reportSub.getAuditRes() != 0)
                                auditRealSize++;
                            if ( reportSub.getAuditRes() == 1 )
                                agree++;
                            else if ( reportSub.getAuditRes() == 2 )
                                disagree++;
                        }
                        if (agree >= Math.ceil(reportSubs.size() / 2d)) { // 同意封贴
                            vo.setAuditRealSize(auditRealSize);
                            vo.setAuditRes((byte)1);
                            reportService.updateByPrimaryKeySelective(vo);
                            if(vo.getType() == 0) // 表白
                                loveService.deleteLove(vo.getTargetId());
                            else if (vo.getType() == 1) // 提问
                                questionService.deleteQuestion(vo.getTargetId());
                            else  // 回复
                                replyService.deleteReply(vo.getTargetId());
                        }
                        // 不同意封贴或举报失效，失效时间默认为 3 天，即超过 3 天，该举报还没有结果
                        else if ((disagree >= Math.ceil(reportSubs.size() / 2d)) || Math.abs(currTimeTimeMillis / 1000 - vo.getTime() ) >= 259200){
                            vo.setAuditRealSize(auditRealSize);
                            vo.setAuditRes((byte)2);
                            reportService.updateByPrimaryKeySelective(vo);
                        }
                    } else { // 开始分配审核任务，将当前举报对象 vo 分配给用户或评审员
                        alloc = true;
                        // 优先分配给评审员
                        List<JudgeVo> judgeVos = judgeService.selectJudgeVosByRange(userId, vo.getAuditSize());
                        originalUserId = userId;
                        if (judgeVos != null && judgeVos.size() == vo.getAuditSize()) {
                            for (JudgeVo judgeVo : judgeVos) {
                                ReportSub reportSub = new ReportSub();
                                reportSub.setUserId(judgeVo.getUserId());
                                reportSub.setParentId(vo.getId());
                                // 插入 ReportSub 对象
                                reportSubService.insertSelective(reportSub);
                                // todo：需要更新 judgeVo 对象
                                judgeVo.setCurrTask(judgeVo.getCurrTask() + 1); // 更新当前任务数
                                judgeVo.setUnfinished(judgeVo.getUnfinished() + 1); // 更新未完成的任务数
                                judgeService.updateByPrimaryKeySelective(judgeVo);
                                userId = Math.max(judgeVo.getUserId(),userId); // 更新 userId 的值，表示下次从哪个评审员开始分配审核任务
                                logger.info("alloc audit task to " + judgeVo.getUserId());
                            }
                        } else {
                            // todo：调用远程接口随机获取用户
                            Res<?> res = userServiceRpc.ordinaryAopSelectUserIds(vo.getAuditSize());
                            if (res == null || res.getState().getCode() != 200)
                                continue;
                            List<Long> userIds = (List<Long>) res.getResult();
                            if (userIds == null || userIds.isEmpty() || userIds.size() != vo.getAuditSize())
                                continue;
                            for (Long user_id : userIds) {
                                ReportSub reportSub = new ReportSub();
                                reportSub.setUserId(user_id);
                                reportSub.setParentId(vo.getId());
                                // 插入 ReportSub 对象
                                reportSubService.insertSelective(reportSub);
                            }
                        }
                    }
                }
                if (reportVos.size() < pageSize)
                    reportVoId = reportMapper.selectMinId();
            } catch (Throwable e) {
                e.printStackTrace();
                userId = originalUserId; // 恢复成原来的值
                reportVoId = originalReportVoId;
                if (currReportVo != null && alloc) { // 只有当分配审核任务出现异常时，才执行 if 中的逻辑
                    reportSubService.invalidReportSubByParentId(currReportVo.getId()); // 让 parentId 相同的审核任务失效掉
                    logger.error(Thread.currentThread().getName() + " has error, currReportVo.getId() is " + currReportVo.getId());
                }
                logger.error(Thread.currentThread().getName() + " has error");
            }finally {
            }

            // todo:2、将过期的审核任务置为失效并且系统自动审核，系统自动审核结果为 已审核，不同意封贴
            try {
                String currTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                // todo：查询过期的审核
                originalReportSubId = reportSubId;
                // 查询过期的审核任务，过期时间默认为 3 天
                List<ReportSub> reportSubs = reportSubService.selectOverdueReportSub(reportSubId,currTime,
                        259200, pageSize);
                // 将过期的审核任务置为失效
                for (ReportSub reportSub : reportSubs) {
                    reportSub.setAuditRes((byte)2).setIsValid((byte)0).setVo_time(currTime);
                    reportSubService.updateByPrimaryKeySelective(reportSub);
                    // todo：如果本次审核任务应该由评审员完成，此时需要更新该评审员的相关信息
                    JudgeVo judgeVo = judgeService.selectByPrimaryKey(reportSub.getUserId(), false);
                    if (judgeVo != null && judgeVo.getJudge()){
                        judgeVo.setCurrTask(judgeVo.getCurrTask() - 1); // 更新当前任务数
                        judgeService.updateByPrimaryKeySelective(judgeVo);
                    }
                    reportSubId = Math.max(reportSub.getId(),reportSubId);
                }
                if (reportSubs.size() < pageSize)
                    reportSubId = reportSubService.selectMinId();
            }catch (Throwable throwable){
                reportSubId = originalReportSubId;
                throwable.printStackTrace();
                logger.error("update invalid audit task failure");
            }
            logger.info("finish alloc audit task");

            // todo:统计点赞数量
            try {
                logger.info("statistical like count");
                List<Long> ids = loveService.selectLovesIdByRange(loveParentId,pageSize);
                for (Long id : ids) {
                    loveParentId = Math.max(id,loveParentId);
                    Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte)0,id);
                    loveService.updateLikeNumberById(id,count);
                }
                ids = questionService.selectQuestionsIdByRange(questionId,pageSize);
                for (Long id : ids) {
                    questionId = Math.max(id,questionId);
                    Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte) 1, id);
                    questionService.updateLikeNumberById(id,count);
                }

                ids = replyService.selectReplysIdByRange(replyId,pageSize);
                for (Long id : ids) {
                    replyId = Math.max(replyId,id);
                    Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte) 2, id);
                    replyService.updateLikeNumberById(id,count);
                }
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    },"alloc-audit-task-thread");

    @Transactional
    @Override
    public int insertSelective(ReportVo record) {
        if ( record.getUserId() == null || record.getType() == null || record.getTargetId() == null || record.getDescribe() == null || record.getImage() == null)
            return 0;
        record.setId(idWorker.nextId());
        record.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return reportMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ReportVo record) {
        if (record.getId() == null)
            return 0;
        if (record.getAuditRes() == null && record.getAuditRealSize() == null)
            return 0;
        return reportMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public ReportVo selectByPrimaryKey(Long id) {
        return reportMapper.selectByPrimaryKey(id);
    }


    @Transactional(readOnly = true)
    @Override
    public Pageable<Long, ReportVo> selectReportVosByUserId(Pageable<Long, ReportVo> pageable) {
//        pageable.setFrom((pageable.getPageNum() - 1) * pageable.getPageSize());
        List<ReportVo> reportVos = reportMapper.selectReportVosByUserId(pageable);
        pageable.setRes(reportVos);
        pageable.setEntity(null);
        return pageable;
    }

    // 查询 id 大于 reportVoId 的记录，取 size 条
    @Transactional(readOnly = true)
    @Override
    public List<ReportVo> selectReportVosByRange(Long reportVoId, int size) {
        return reportMapper.selectReportVosByRange(reportVoId, size);
//        if (reportVos.size() < size){
//            reportVos.addAll(reportMapper.selectReportVosByRange(reportMapper.selectMinId(),size - reportVos.size()));
//        }
//        return reportVos;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        thread.start();
    }
}
