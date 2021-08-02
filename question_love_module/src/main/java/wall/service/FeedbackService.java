package wall.service;

import wall.pojo.Feedback;

public interface FeedbackService {

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long id);

}
