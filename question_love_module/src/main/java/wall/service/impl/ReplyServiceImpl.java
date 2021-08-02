package wall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.ReplyMapper;
import wall.entity.Pageable;
import wall.pojo.LikeVo;
import wall.pojo.MessageVo;
import wall.pojo.ReplyVo;
import wall.service.BaseService;
import wall.service.LikeService;
import wall.service.MessageService;
import wall.service.ReplyService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReplyServiceImpl extends BaseService<ReplyVo> implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LikeService likeService;

    Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);


    @Autowired
    private MessageService messageService;

    @Transactional
    @Override
    public int insertSelective(ReplyVo record,Long userId) {
        if (record.getUserId() == null || record.getTargetId() == null || record.getType() == null || record.getPost_id() == null)
            return 0;
        record.setId(idWorker.nextId());
        record.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int res = replyMapper.insertSelective(record);
        if (res > 0){
            MessageVo messageVo = new MessageVo();
            messageVo.setTargetId(record.getId());
            messageVo.setUserId(userId);
            messageVo.setType((byte)1);
            messageVo.setSendUserId(record.getUserId());
            return messageService.addMessage(messageVo);
        }
        return res;
    }

    @Transactional(readOnly = true)
    @Override
    public ReplyVo selectByPrimaryKey(Long id,Long userId) {
        ReplyVo replyVo = replyMapper.selectByPrimaryKey(id);
        if (userId != null){
            LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 2, id);
            replyVo.setLike(likeVo);
        }
        return replyVo;
    }

    // 只取 5 条
    @Transactional(readOnly = true)
    @Override
    public List<ReplyVo> selectByTypeAndTargetIdLimit5(byte type, Long targetId,Long userId) {
        List<ReplyVo> replyVos = replyMapper.selectByTypeAndTargetId(type, targetId);
        for (ReplyVo replyVo : replyVos) {
            replyVo.setHaveSub(replyMapper.count(replyVo.getId()) > 0);
            if (!replyVo.getIsAnonymous()){
                setUserNameAndAvatars(replyVo);
            }
            if (userId != null){
                LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 2, replyVo.getId());
                replyVo.setLike(likeVo);
            }
        }
        return replyVos;
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ReplyVo record) {
        if (record.getId() == null)
            return 0;
        return replyMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<ReplyVo,ReplyVo> selectList(Pageable<ReplyVo,ReplyVo> pageable,Long userId) {
        if (pageable.getEntity() != null && (pageable.getEntity().getType() == null || pageable.getEntity().getTargetId() == null))
            return null;
        List<ReplyVo> replyVos = replyMapper.selectList(pageable);
        pageable.setRes(replyVos);
        for (ReplyVo replyVo : replyVos) {
            replyVo.setHaveSub(replyMapper.count(replyVo.getId()) > 0);
            if (!replyVo.getIsAnonymous()){
                setUserNameAndAvatars(replyVo);
            }
            if (userId != null){
                LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 2, replyVo.getId());
                replyVo.setLike(likeVo);
            }
        }
        pageable.setEntity(null);
        return pageable;
    }

    @Transactional
    @Override
    public int deleteReply(Long targetId) {
        logger.info("deleteReply，id is " + targetId);
        return replyMapper.deleteReply(targetId);
    }

    @Transactional
    @Override
    public int deleteReplyByUser(Long userId, Long id) {
        return replyMapper.deleteReplyByUser(userId, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> selectReplysIdByRange(long replyId, int pageSize) {
        return replyMapper.selectReplysIdByRange(replyId,pageSize);
    }

    @Transactional
    @Override
    public int updateLikeNumberById(Long id, Integer count) {
        return replyMapper.updateLikeNumberById(id,count);
    }
}
