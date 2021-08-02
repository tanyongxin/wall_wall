package wall.pojo;

public class MessageVo extends Message {

    private String vo_time;

    private String sendUserName;

    private String sendUserAvatars;

    private String content; // 其他用户回复的内容

    public String getContent() {
        return content;
    }

    public MessageVo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSendUserAvatars() {
        return sendUserAvatars;
    }

    public MessageVo setSendUserAvatars(String sendUserAvatars) {
        this.sendUserAvatars = sendUserAvatars;
        return this;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public MessageVo setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public MessageVo setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                ", sendUserName='" + sendUserName + '\'' +
                ", sendUserAvatars='" + sendUserAvatars + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
