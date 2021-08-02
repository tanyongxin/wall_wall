package wall.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.LikeMapper;
import wall.dao.LoveParentMapper;
import wall.dao.QuestionsMapper;
import wall.dao.ReplyMapper;
import wall.entity.Pageable;
import wall.pojo.LikeVo;
import wall.pojo.MessageVo;
import wall.service.BaseService;
import wall.service.LikeService;
import wall.service.MessageService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LikeServiceImpl extends BaseService implements LikeService {

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MessageService messageService;

//    @Autowired
//    private LoveParentMapper loveParentMapper;
//
//    @Autowired
//    private ReplyMapper replyMapper;
//
//    @Autowired
//    private QuestionsMapper questionsMapper;

    @Transactional
    @Override
    public int insertSelective(LikeVo record,Long userId) {
        if (record.getUserId() == null || record.getType() == null || record.getTargetId() == null)
            return 0;
        // 查询用户是否点过赞
        LikeVo likeVo;
        if ((likeVo = selectIsLikeByUerIdAndTypeAndTargetIId(record.getUserId(),record.getType(),record.getTargetId())) != null) {
            return like(likeVo.getId(), record.getUserId());
        }
        record.setId(idWorker.nextId());
        record.setUpdate_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int res = likeMapper.insertSelective(record);
        if (res > 0){
            MessageVo messageVo = new MessageVo();
            messageVo.setTargetId(record.getId());
            messageVo.setUserId(userId);
            messageVo.setType((byte)0);
            messageVo.setSendUserId(record.getUserId());
            return messageService.addMessage(messageVo);
        }
        return res;
    }

    @Transactional
    @Override
    public int cancelLike(Long id,Long userId) {
        return likeMapper.cancelLike(id,userId);
    }

    @Transactional
    @Override
    public int like(Long id,Long userId) {
        return likeMapper.like(id,userId);
    }

    @Transactional
    @Override
    public LikeVo selectIsLikeByUerIdAndTypeAndTargetIId(Long userId, Byte type, Long targetId) {
        return likeMapper.selectIsLikeByUerIdAndTypeAndTargetIId(userId, type, targetId);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<Long,LikeVo> selectLikeVosByUserId(Pageable<Long,LikeVo> pageable) {
        List<LikeVo> likeVos = likeMapper.selectLikeVosByUserId(pageable);
        pageable.setRes(likeVos);
        pageable.setEntity(null);
        return pageable;
    }

    // 统计 （type，targetId） 所对应的帖子的点赞数
    @Transactional(readOnly = true)
    @Override
    public Integer selectLikeCountByTypeAndTargetIId(Byte type, Long targetId) {
        return likeMapper.selectLikeCountByTypeAndTargetIId(type,targetId);
    }


}
