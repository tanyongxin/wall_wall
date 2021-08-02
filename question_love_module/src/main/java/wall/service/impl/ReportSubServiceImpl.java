package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.ReportSubMapper;
import wall.entity.Pageable;
import wall.pojo.JudgeVo;
import wall.pojo.ReportSub;
import wall.pojo.ReportVo;
import wall.service.JudgeService;
import wall.service.ReportService;
import wall.service.ReportSubService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportSubServiceImpl implements ReportSubService {

    @Autowired
    private ReportSubMapper reportSubMapper;

    @Autowired
    private ReportService reportService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JudgeService judgeService;

    @Transactional
    @Override
    public int insertSelective(ReportSub record) {
        if (record.getUserId() == null || record.getParentId() == null)
            return 0;
        record.setId(idWorker.nextId());
        record.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return reportSubMapper.insertSelective(record);
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ReportSub record) {
        if (record.getId() == null)
            return 0;
        return reportSubMapper.updateByPrimaryKeySelective(record);
    }

    // threshold 超过多少时间算失效
    @Transactional(readOnly = true)
    @Override
    public List<ReportSub> selectOverdueReportSub(Long id,String currTime,long threshold,int pageSize) {
        return reportSubMapper.selectOverdueReportSub(id,currTime,threshold, pageSize);
    }

    // 测试完成
    @Transactional
    @Override
    public int invalidReportSubByParentId(Long parentId) {
        return reportSubMapper.invalidReportSubByParentId(parentId);
    }


    @Transactional(readOnly = true)
    @Override
    public List<ReportSub> selectByParentId(Long parentId) {
        return reportSubMapper.selectByParentId(parentId);
    }


    @Transactional(readOnly = true)
    @Override
    public Pageable<Long, ReportSub> selectByUserId(Pageable<Long, ReportSub> pageable) {
        pageable.setFrom((pageable.getPageNum() - 1) * pageable.getPageSize());
        List<ReportSub> reportSubs = reportSubMapper.selectByUserId(pageable);
        pageable.setRes(reportSubs);
        for (ReportSub reportSub : reportSubs) {
            reportSub.setReport(reportService.selectByPrimaryKey(reportSub.getParentId()));
        }
        return pageable;
    }

    @Transactional
    @Override
    public String audit(Long reportSubId, Long userId,Byte auditRes) {
        ReportSub reportSub = reportSubMapper.selectByPrimaryKey(reportSubId);
        if (reportSub == null || !reportSub.getUserId().equals(userId))
            return "非法请求";
        if (reportSub.getIsValid() == 0 )
            return "审核已失效，系统已自动审核";
        if (reportSub.getAuditRes() != 0)
            return "审核已完成";
        ReportVo reportVo = reportService.selectByPrimaryKey(reportSub.getParentId());
        if (reportVo == null)
            return "非法请求";
        if(reportVo.getAuditRes() != 0)
            return "该举报已审核完毕";
        long currTimeTimeMillis = System.currentTimeMillis();
        // 时间默认为 3 天
        if (Math.abs(currTimeTimeMillis / 1000 - reportSub.getTime() ) >= 259200) {
//            System.out.println("xxx");
            return "审核已失效，系统已自动审核";
        }
        reportSub.setAuditRes(auditRes);
        reportSub.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        reportSub.setIsValid((byte)1);
        reportSubMapper.updateByPrimaryKeySelective(reportSub);
        JudgeVo judgeVo = judgeService.selectByPrimaryKey(userId, false);
        if (judgeVo != null && judgeVo.getJudge()){
            judgeVo.setCurrTask(judgeVo.getCurrTask() - 1);
            judgeVo.setComplete(judgeVo.getComplete() + 1);
            judgeVo.setUnfinished(judgeVo.getUnfinished() - 1);
            judgeService.updateByPrimaryKeySelective(judgeVo);
        }
        return "success";
    }

    @Transactional(readOnly = true)
    @Override
    public ReportSub selectById(Long id) {
        ReportSub reportSub = reportSubMapper.selectByPrimaryKey(id);
        if (reportSub == null)
            return null;
        reportSub.setReport(reportService.selectByPrimaryKey(reportSub.getParentId()));
        return reportSub;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReportSub> selectAll() {
        return reportSubMapper.selectAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Long selectMinId() {
        return reportSubMapper.selectMinId();
    }


}
