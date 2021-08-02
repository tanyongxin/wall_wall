package wall.pojo;

public class Message extends BasePojo {

    private Long targetId;

    private String vo_targetId;

    private Long sendUserId; // 点赞用户、评论用户的 id

    private String vo_sendUserId;

    private Byte type; // 消息类型，0 表示点赞（即有用户给自己的点赞），1 表示回复（即有用户回复了自己），2 表示有举报任务需要用户完成

    private Boolean isView; // 是否已查看

    public String getVo_sendUserId() {
        return vo_sendUserId;
    }

    public Message setVo_sendUserId(String vo_sendUserId) {
        this.vo_sendUserId = vo_sendUserId;
        this.sendUserId = Long.parseLong(vo_sendUserId);
        return this;
    }

    public Boolean getView() {
        return isView;
    }

    public Message setView(Boolean view) {
        isView = view;
        return this;
    }

    public Long getSendUserId() {
        return sendUserId;
    }

    public Message setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
        this.vo_sendUserId = sendUserId + "";
        return this;
    }

    public String getVo_targetId() {
        return vo_targetId;
    }

    public Message setVo_targetId(String vo_targetId) {
        this.vo_targetId = vo_targetId;
        this.targetId = Long.parseLong(vo_targetId);
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Message setTargetId(Long targetId) {
        this.targetId = targetId;
        this.vo_targetId = targetId + "";
        return this;
    }

    public Byte getType() {
        return type;
    }

    public Message setType(Byte type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                super.toString() +
                "targetId=" + targetId +
                ", vo_targetId='" + vo_targetId + '\'' +
                ", sendUserId=" + sendUserId +
                ", vo_sendUserId='" + vo_sendUserId + '\'' +
                ", type=" + type +
                ", isView=" + isView +
                '}';
    }
}
