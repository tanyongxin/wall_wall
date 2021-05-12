package wall.pojo;

public class Questions {

    private Long id;

    private Long userId;

    private Integer time;

    private String toPerson = "";

    private Boolean isAnonymous = true;

    private Boolean isValid = true;

    private Byte isAudit = 0;

    private String content;

    private Byte backgroundType; // 两种，img 或 color。0 表示 img，1 表示 color

    private String backgroundValue;

    private String questionBackground;

    private String questionColor;

    private String questionOpacity;

    private Long topicId;

    private String title = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getToPerson() {
        return toPerson;
    }

    public void setToPerson(String toPerson) {
        this.toPerson = toPerson == null ? null : toPerson.trim();
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Byte getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Byte isAudit) {
        this.isAudit = isAudit;
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
}
