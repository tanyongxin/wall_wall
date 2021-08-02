package wall.pojo;


// 回复消息实体类
public class ReplyMessage extends MessageVo{

    private ReplyVo replyVo;

//    private MessageVo messageVo;
//
//    public MessageVo getMessageVo() {
//        return messageVo;
//    }
//
//    public ReplyMessage setMessageVo(MessageVo messageVo) {
//        this.messageVo = messageVo;
//        return this;
//    }

    public ReplyVo getReplyVo() {
        return replyVo;
    }

    public ReplyMessage setReplyVo(ReplyVo replyVo) {
        this.replyVo = replyVo;
        return this;
    }
}
