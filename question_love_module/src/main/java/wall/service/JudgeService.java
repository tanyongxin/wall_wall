package wall.service;

import wall.pojo.JudgeVo;
import java.util.List;

public interface JudgeService {

    // 测试完成
    int insertSelective(JudgeVo record);

    // 测试完成
    JudgeVo selectByPrimaryKey(Long userId,boolean needUserName);

    // 测试完成
    int updateByPrimaryKeySelective(JudgeVo record);

    // 测试完成
    int agree(Long userId);

    // 测试完成
    int disagree(Long userId,String reason);

    // 选出大于 userId 的 JudgeVo，只选取 size 个。测试完成
    List<JudgeVo> selectJudgeVosByRange(Long userId, int size);

}
