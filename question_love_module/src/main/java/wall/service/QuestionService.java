package wall.service;

import wall.entity.Pageable;
import wall.pojo.QuestionsVo;

public interface QuestionService {


    int addQuestion(QuestionsVo questionsVo);

    QuestionsVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionsVo record);

    Pageable<QuestionsVo> selectQuestionsVos(Pageable<QuestionsVo> pageable);

}
