package wall.service;

import wall.entity.Pageable;
import wall.pojo.Message;
import wall.pojo.MessageVo;

public interface MessageService {

    int addMessage(MessageVo messageVo);

    Pageable<MessageVo, MessageVo> selectMessageVos(Pageable<MessageVo, MessageVo> pageable);

    int view(Long id);

    int selectCountByUserId(Long userId);
}
