package wall.dao;

import org.springframework.stereotype.Service;
import wall.entity.Pageable;
import wall.pojo.QuestionsVo;

import java.util.List;

@Service
public interface QuestionsMapper {


    int insertSelective(QuestionsVo record);

    QuestionsVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuestionsVo record);

    // 根据 QuestionsVo 对象中封装的条件进行查询
    List<QuestionsVo> selectQuestionsVos(Pageable<QuestionsVo> pageable);

}
