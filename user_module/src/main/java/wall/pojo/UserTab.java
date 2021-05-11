package wall.pojo;

public class UserTab {

    private Long id;

    private String wxName;

    private String oppenId;

    private String sex;

    private Byte identity; // 用户属于什么身份，学生还是其他

    private Integer schoolId;

    private Integer joinTime; // 加入时间

    private Boolean isVip; // 是否是 vip

    private String wxNum; // 用户微信号

    public Long getId() {
        return id;
    }

    public UserTab setId(Long id) {
        this.id = id;
        return this;
    }

    public String getWxName() {
        return wxName;
    }

    public UserTab setWxName(String wxName) {
        this.wxName = wxName;
        return this;
    }

    public String getOppenId() {
        return oppenId;
    }

    public UserTab setOppenId(String oppenId) {
        this.oppenId = oppenId;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UserTab setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Byte getIdentity() {
        return identity;
    }

    public UserTab setIdentity(Byte identity) {
        this.identity = identity;
        return this;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public UserTab setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
        return this;
    }

    public Integer getJoinTime() {
        return joinTime;
    }

    public UserTab setJoinTime(Integer joinTime) {
        this.joinTime = joinTime;
        return this;
    }

    public Boolean getVip() {
        return isVip;
    }

    public UserTab setVip(Boolean vip) {
        isVip = vip;
        return this;
    }

    public String getWxNum() {
        return wxNum;
    }

    public UserTab setWxNum(String wxNum) {
        this.wxNum = wxNum;
        return this;
    }
}
