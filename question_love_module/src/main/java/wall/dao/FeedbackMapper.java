package wall.dao;


import org.springframework.stereotype.Repository;
import wall.pojo.Feedback;

@Repository
public interface FeedbackMapper {

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long id);

}
