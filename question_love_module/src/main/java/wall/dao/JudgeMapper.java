package wall.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.pojo.JudgeVo;

import java.util.List;

@Repository
public interface JudgeMapper {

    int insertSelective(JudgeVo record);

    JudgeVo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(JudgeVo record);

    int agree(@Param("userId") Long userId, @Param("reason") String reason);

    int disagree(@Param("userId") Long userId, @Param("reason") String reason);

    Long selectMinUserId();

    List<JudgeVo> selectJudgeVosByRange(@Param("userId") Long userId, @Param("size") int size);
}
