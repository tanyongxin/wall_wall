package wall.service;

import wall.entity.Pageable;
import wall.pojo.QuestionsVo;

import java.util.List;

public interface QuestionService {


    int addQuestion(QuestionsVo questionsVo);

    QuestionsVo selectByPrimaryKey(Long id,Long userId);

    int updateByPrimaryKeySelective(QuestionsVo record);

    Pageable<QuestionsVo,QuestionsVo> selectQuestionsVos(Pageable<QuestionsVo,QuestionsVo> pageable,Long userId);

    int deleteQuestion(Long targetId);

    int deleteQuestionByUser(Long userId, Long id);

    List<Long> selectQuestionsIdByRange(long questionId, int pageSize);

    int updateLikeNumberById(Long id, Integer count);
}
