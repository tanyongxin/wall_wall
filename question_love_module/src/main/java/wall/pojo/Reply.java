package wall.pojo;

public class Reply extends BasePojo {

    private Boolean isAnonymous = true; // 是否匿名，true 表示匿名，false 表示实名

    private Boolean isValid = true; // 是否有效，true 表示有效，false 表示无效。如果后期有用户举报该帖子，并且举报的最终结果为封贴，则 isValid 字段置为 false。如果用户自己删除，则该字段也为 false

    private Byte isAudit ; // 0 表示未审核，1 表示已审核未通过，2 表示已审核已通过。该审核字段表示管理员审核后的结果，如果后期有用户举报该帖子，则 isAudit 字段不会修改。

    private String content; // 回复内容

    private Long targetId; // 被回复内容的 id

    private Byte type; // 表示回复的类型，0 表示该回复回复的是表白，1 表示该回复回复的是提问，2 表示该回复回复的是其他人的回复

    private int like_number = 0; // 点赞数量

    private Long post_id; // 帖子 id，即回复所属的帖子

    private String vo_post_id;

    public String getVo_post_id() {
        return vo_post_id;
    }

    public Reply setVo_post_id(String vo_post_id) {
        this.vo_post_id = vo_post_id;
        this.post_id = Long.parseLong(vo_post_id);
        return this;
    }

    public Long getPost_id() {
        return post_id;
    }

    public Reply setPost_id(Long post_id) {
        this.post_id = post_id;
        this.vo_post_id = post_id + "";
        return this;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public BasePojo setIsAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
        return this;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public BasePojo setIsValid(Boolean valid) {
        isValid = valid;
        return this;
    }

    public Byte getIsAudit() {
        return isAudit;
    }

    public BasePojo setIsAudit(Byte isAudit) {
        this.isAudit = isAudit;
        return this;
    }

    public int getLike_number() {
        return like_number;
    }

    public Reply setLike_number(int like_number) {
        this.like_number = like_number;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reply{" +
                super.toString() +
                "isAnonymous=" + isAnonymous +
                ", isValid=" + isValid +
                ", isAudit=" + isAudit +
                ", content='" + content + '\'' +
                ", targetId=" + targetId +
                ", type=" + type +
                ", like_number=" + like_number +
                ", post_id=" + post_id +
                ", vo_post_id='" + vo_post_id + '\'' +
                '}';
    }
}
