package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.MessageMapper;
import wall.entity.Pageable;
import wall.pojo.Message;
import wall.pojo.MessageVo;
import wall.pojo.ReplyVo;
import wall.pojo.UserStub;
import wall.service.*;
import wall.util.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageServiceImpl extends BaseService<MessageVo> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ReplyService replyService;

//    @Autowired
//    private LikeService likeService;
//
//    @Autowired
//    private ReportSubService reportSubService;

    @Transactional
    @Override
    public int addMessage(MessageVo messageVo) {
        if (messageVo.getUserId() == null || messageVo.getTargetId() == null || messageVo.getType() == null || messageVo.getSendUserId() == null)
            return 0;
        messageVo.setId(idWorker.nextId());
        messageVo.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return messageMapper.insertSelective(messageVo);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<MessageVo, MessageVo> selectMessageVos(Pageable<MessageVo, MessageVo> pageable) {
        MessageVo messageVo = pageable.getEntity();
        if ( messageVo == null || messageVo.getUserId() == null )
            return pageable;
        List<MessageVo> messageVos = messageMapper.selectMessageVos(pageable);
        for (MessageVo vo : messageVos) {
            if (vo.getType() == 1) { // 回复消息
                ReplyVo replyVo = replyService.selectByPrimaryKey(vo.getTargetId(),null);
                if (replyVo != null && replyVo.getIsAudit() == 2 && replyVo.getIsValid()) { // 只有当有效，才返回
                    if (!replyVo.getIsAnonymous()) {
                        UserStub userStub = getUserStubByUserId(vo.getSendUserId());
                        if (userStub != null) {
                            vo.setSendUserName(userStub.getWxName());
                            vo.setSendUserAvatars(userStub.getAvatars());
                        }
                    }
                    vo.setContent(replyVo.getContent());
                }
            }
            else if (vo.getType() == 0){
                UserStub userStub = getUserStubByUserId(vo.getSendUserId());
                if (userStub != null){
                    vo.setSendUserName(userStub.getWxName());
                    vo.setSendUserAvatars(userStub.getAvatars());
                }
            }
        }
        pageable.setEntity(null);
        pageable.setRes(messageVos);
        return pageable;
    }

    @Transactional
    @Override
    public int view(Long id) {
        if (id == null || id <= 0)
            return 0;
        return messageMapper.view(id);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCountByUserId(Long userId) {
        return messageMapper.selectCountByUserId(userId);
    }
}
