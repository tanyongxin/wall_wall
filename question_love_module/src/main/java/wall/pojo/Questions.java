package wall.pojo;

public class Questions extends BasePojo {

    private Boolean isAnonymous = true; // 是否匿名，true 表示匿名，false 表示实名

    private Boolean isValid = true; // 是否有效，true 表示有效，false 表示无效。如果后期有用户举报该帖子，并且举报的最终结果为封贴，则 isValid 字段置为 false。如果用户自己删除，则该字段也为 false

    private Byte isAudit ; // 0 表示未审核，1 表示已审核未通过，2 表示已审核已通过。该审核字段表示管理员审核后的结果，如果后期有用户举报该帖子，则 isAudit 字段不会修改。

    private String toPerson = "";

    private String content;

    private Byte backgroundType; // 两种，img 或 color。0 表示 img，1 表示 color

    private String backgroundValue;

    private String questionBackground;

    private String questionColor;

    private String questionOpacity;

    private Long topicId;

    private String title = "";

    private int like_number = 0; // 点赞数量

    private Integer school_id = 0; // 学校 id

    private Byte type; // 提问类型，0 表示发送到广场（所有人可见），1 表示分享给朋友（仅部分人可见），2 表示既发送到广场也分享给朋友

    public Byte getType() {
        return type;
    }

    public Questions setType(Byte type) {
        this.type = type;
        return this;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public Questions setSchool_id(Integer school_id) {
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

    public Questions setLike_number(int like_number) {
        this.like_number = like_number;
        return this;
    }

    public String getToPerson() {
        return toPerson;
    }

    public void setToPerson(String toPerson) {
        this.toPerson = toPerson == null ? null : toPerson.trim();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(Byte backgroundType) {
        this.backgroundType = backgroundType;
    }

    public String getBackgroundValue() {
        return backgroundValue;
    }

    public void setBackgroundValue(String backgroundValue) {
        this.backgroundValue = backgroundValue == null ? null : backgroundValue.trim();
    }

    public String getQuestionBackground() {
        return questionBackground;
    }

    public void setQuestionBackground(String questionBackground) {
        this.questionBackground = questionBackground == null ? null : questionBackground.trim();
    }

    public String getQuestionColor() {
        return questionColor;
    }

    public void setQuestionColor(String questionColor) {
        this.questionColor = questionColor == null ? null : questionColor.trim();
    }

    public String getQuestionOpacity() {
        return questionOpacity;
    }

    public void setQuestionOpacity(String questionOpacity) {
        this.questionOpacity = questionOpacity == null ? null : questionOpacity.trim();
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Override
    public String toString() {
        return "Questions{" +
                super.toString() +
                "isAnonymous=" + isAnonymous +
                ", isValid=" + isValid +
                ", isAudit=" + isAudit +
                ", toPerson='" + toPerson + '\'' +
                ", content='" + content + '\'' +
                ", backgroundType=" + backgroundType +
                ", backgroundValue='" + backgroundValue + '\'' +
                ", questionBackground='" + questionBackground + '\'' +
                ", questionColor='" + questionColor + '\'' +
                ", questionOpacity='" + questionOpacity + '\'' +
                ", topicId=" + topicId +
                ", title='" + title + '\'' +
                ", like_number=" + like_number +
                ", school_id=" + school_id +
                ", type=" + type +
                '}';
    }
}
