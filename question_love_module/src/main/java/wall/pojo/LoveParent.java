package wall.pojo;

import wall.entity.Pageable;

// 表白的主表
public class LoveParent extends BasePojo{


    private Boolean isAnonymous = true; // 是否匿名，true 表示匿名，false 表示实名

    private Boolean isValid = true; // 是否有效，true 表示有效，false 表示无效。如果后期有用户举报该帖子，并且举报的最终结果为封贴，则 isValid 字段置为 false。如果用户自己删除，则该字段也为 false

    private Byte isAudit ; // 0 表示未审核，1 表示已审核未通过，2 表示已审核已通过。该审核字段表示管理员审核后的结果，如果后期有用户举报该帖子，则 isAudit 字段不会修改。

    private Long topicId; // 所属话题 id

    private String toPerson = ""; // 被表白的人 id，可以有多个，用逗号隔开

    private int like_number = 0; // 点赞数量

    private Integer school_id = 0; // 学校 id


    public Integer getSchool_id() {
        return school_id;
    }

    public LoveParent setSchool_id(Integer school_id) {
        this.school_id = school_id;
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

    public LoveParent setLike_number(int like_number) {
        this.like_number = like_number;
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

    @Override
    public String toString() {
        return "LoveParent{" +
                super.toString() +
                "isAnonymous=" + isAnonymous +
                ", isValid=" + isValid +
                ", isAudit=" + isAudit +
                ", topicId=" + topicId +
                ", toPerson='" + toPerson + '\'' +
                ", like_number=" + like_number +
                ", school_id=" + school_id +
                '}';
    }
}
