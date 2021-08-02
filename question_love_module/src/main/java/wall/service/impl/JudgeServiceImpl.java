package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.JudgeMapper;
import wall.pojo.JudgeVo;
import wall.pojo.UserStub;
import wall.service.BaseService;
import wall.service.JudgeService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class JudgeServiceImpl extends BaseService<JudgeVo> implements JudgeService {


    @Autowired
    private JudgeMapper judgeMapper;


    @Transactional
    @Override
    public int insertSelective(JudgeVo record) {
        record.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return judgeMapper.insertSelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public JudgeVo selectByPrimaryKey(Long userId,boolean needUserName) {
        JudgeVo judgeVo = judgeMapper.selectByPrimaryKey(userId);
        if (needUserName && judgeVo != null) {
            setUserNameAndAvatars(judgeVo);
        }
        return judgeVo;
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(JudgeVo record) {
        if (record.getUserId() == null)
            return 0;
        return judgeMapper.updateByPrimaryKeySelective(record);
    }

    // 同意某个用户成为审核员
    @Transactional
    @Override
    public int agree(Long userId) {
        if (userId == null)
            return 0;
        return judgeMapper.agree(userId,"apply success");
    }

    // 不同意某个用户成为审核员
    @Transactional
    @Override
    public int disagree(Long userId,String reason) {
        if (userId == null || reason == null || reason.length() == 0 || reason.length() > 50)
            return 0;
        return judgeMapper.disagree(userId,reason);
    }

    @Transactional(readOnly = true)
    @Override
    public List<JudgeVo> selectJudgeVosByRange(Long userId, int size) {
//        if (userId == 0)
//            userId = judgeMapper.selectMinUserId();
        List<JudgeVo> judgeVos = judgeMapper.selectJudgeVosByRange(userId, size);
        if (judgeVos.size() < size){
            List<JudgeVo> judgeVos1 = judgeMapper.selectJudgeVosByRange(judgeMapper.selectMinUserId(), size - judgeVos.size());
            judgeVos.addAll(judgeVos1);
        }
        return judgeVos;
    }
}
