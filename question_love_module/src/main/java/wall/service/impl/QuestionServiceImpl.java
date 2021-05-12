package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.QuestionsMapper;
import wall.entity.Pageable;
import wall.pojo.QuestionsVo;
import wall.service.BaseService;
import wall.service.QuestionService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class QuestionServiceImpl extends BaseService implements QuestionService {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private IdWorker idWorker;

    @Transactional
    @Override
    public int addQuestion(QuestionsVo questionsVo) {
        if (questionsVo.getUserId() == null)
            throw new RuntimeException();
        questionsVo.setId(idWorker.nextId());
        questionsVo.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return questionsMapper.insertSelective(questionsVo);
    }

    @Transactional(readOnly = true)
    @Override
    public QuestionsVo selectByPrimaryKey(Long id) {
        QuestionsVo questionsVo = questionsMapper.selectByPrimaryKey(id);
        // todo:调用远程接口获取用户名。只有实名才需要
        if (!questionsVo.getIsAnonymous())
            questionsVo.setUserName(getUserNameByUserId(questionsVo.getUserId()));
        // todo：获取提问的回复信息
        return questionsVo;
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(QuestionsVo record) {
        if (record.getId() == null)
            throw new RuntimeException();
        return questionsMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<QuestionsVo> selectQuestionsVos(Pageable<QuestionsVo> pageable) {
        pageable.setFrom((pageable.getPageNum() - 1) * pageable.getPageSize());
        List<QuestionsVo> questionsVos = questionsMapper.selectQuestionsVos(pageable);
        pageable.setRes(questionsVos);
        Long lastId = 0L;
        for (QuestionsVo questionsVo : questionsVos) {
            // todo:调用远程接口获取用户名。只有实名才需要
            if (!questionsVo.getIsAnonymous())
                questionsVo.setUserName(getUserNameByUserId(questionsVo.getUserId()));
            lastId = Math.max(lastId,questionsVo.getId());
        }
        if (lastId != 0)
            pageable.setLastId(lastId);
        pageable.setEntity(null);
        return pageable;
    }
}
