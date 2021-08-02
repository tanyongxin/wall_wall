package wall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.QuestionsMapper;
import wall.entity.Pageable;
import wall.pojo.LikeVo;
import wall.pojo.QuestionsVo;
import wall.pojo.ReplyVo;
import wall.service.BaseService;
import wall.service.LikeService;
import wall.service.QuestionService;
import wall.service.ReplyService;
import wall.util.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class QuestionServiceImpl extends BaseService<QuestionsVo> implements QuestionService {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private LikeService likeService;

    Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Transactional
    @Override
    public int addQuestion(QuestionsVo questionsVo) {
        if (questionsVo.getUserId() == null)
            return 0;
        questionsVo.setId(idWorker.nextId());
        questionsVo.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return questionsMapper.insertSelective(questionsVo);
    }

    @Transactional(readOnly = true)
    @Override
    public QuestionsVo selectByPrimaryKey(Long id,Long userId) {
        QuestionsVo questionsVo = questionsMapper.selectByPrimaryKey(id);
        if (questionsVo == null)
            return null;
        // todo:调用远程接口获取用户名。只有实名才需要
        if (!questionsVo.getIsAnonymous()){
            setUserNameAndAvatars(questionsVo);
        }
        // todo：获取提问的回复信息
        Pageable<ReplyVo,ReplyVo> pageable = new Pageable<>();
        ReplyVo replyVo = new ReplyVo();
        replyVo.setType((byte)1);
        replyVo.setTargetId(questionsVo.getId());
        pageable.setEntity(replyVo);
        List<ReplyVo> replyVos = replyService.selectList(pageable,userId).getRes();
        questionsVo.setReplyVos(replyVos);
        // 查询登陆用户的点赞情况
        if (userId != null) {
            LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 1, id);
            questionsVo.setLike(likeVo);
        }
        return questionsVo;
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(QuestionsVo record) {
        if (record.getId() == null)
            return 0;
        return questionsMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<QuestionsVo,QuestionsVo> selectQuestionsVos(Pageable<QuestionsVo,QuestionsVo> pageable,Long userId) {
        List<QuestionsVo> questionsVos = questionsMapper.selectQuestionsVos(pageable);
        pageable.setRes(questionsVos);
        for (QuestionsVo questionsVo : questionsVos) {
            // todo:调用远程接口获取用户名。只有实名才需要
            if (!questionsVo.getIsAnonymous()){
                setUserNameAndAvatars(questionsVo);
            }
            questionsVo.setReplyVos(replyService.selectByTypeAndTargetIdLimit5((byte)1,questionsVo.getId(),userId));
        }
        pageable.setEntity(null);
        return pageable;
    }

    @Transactional
    @Override
    public int deleteQuestion(Long targetId) {
        logger.info("deleteQuestion，id is " + targetId);
        return questionsMapper.deleteQuestion(targetId);
    }

    @Transactional
    @Override
    public int deleteQuestionByUser(Long userId, Long id) {
        return questionsMapper.deleteQuestionByUser(userId,id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> selectQuestionsIdByRange(long questionId, int pageSize) {
        return questionsMapper.selectQuestionsIdByRange(questionId,pageSize);
    }

    @Transactional
    @Override
    public int updateLikeNumberById(Long id, Integer count) {
        return questionsMapper.updateLikeNumberById(id,count);
    }
}
