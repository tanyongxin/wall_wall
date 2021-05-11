package wall.pojo;

// 表白的主表
public class LoveParent {

    private Long id;

    private Integer time; // 发表时间

    private Long topicId; // 所属话题 id

    private String toPerson = ""; // 被表白的人 id，可以有多个，用逗号隔开

    private Long userId; // 发表人 id

    private Boolean isAnonymous = true; // 是否匿名，true 表示匿名，false 表示实名

    private Boolean isValid = true; // 是否有效，true 表示有效，false 表示无效

    private Byte isAudit = 0; // 0 表示未审核，1 表示已审核未通过，2 表示已审核已通过

    public Long getId() {
        return id;
    }

    public LoveParent setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public LoveParent setTime(Integer time) {
        this.time = time;
        return this;
    }

    public Long getTopicId() {
        return topicId;
    }

    public LoveParent setTopicId(Long topicId) {
        this.topicId = topicId;
        return this;
    }

    public String getToPerson() {
        return toPerson;
    }

    public LoveParent setToPerson(String toPerson) {
        this.toPerson = toPerson;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public LoveParent setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public LoveParent setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
        return this;
    }

    public Boolean getValid() {
        return isValid;
    }

    public LoveParent setValid(Boolean valid) {
        isValid = valid;
        return this;
    }

    public Byte getIsAudit() {
        return isAudit;
    }

    public LoveParent setIsAudit(Byte isAudit) {
        this.isAudit = isAudit;
        return this;
    }
}
