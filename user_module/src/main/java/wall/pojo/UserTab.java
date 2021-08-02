package wall.pojo;

public class UserTab extends BasePojo{

    private String avatars; // 用户头像

    private String wxName;

    private String oppenId;

    private String sex;

    private Byte identity; // 用户属于什么身份，学生还是其他

    private Integer schoolId;

    private Integer joinTime; // 加入时间

    private Boolean isVip; // 是否是 vip

    private String wxNum; // 用户微信号

    public String getAvatars() {
        return avatars;
    }

    public UserTab setAvatars(String avatars) {
        this.avatars = avatars;
        return this;
    }

    public String getVo_id() {
        return vo_id;
    }

    public UserTab setVo_id(String vo_id) {
        this.vo_id = vo_id;
        this.id = Long.parseLong(vo_id);
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserTab setId(Long id) {
        this.id = id;
        this.vo_id = id + "";
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

    @Override
    public String toString() {
        return "UserTab{" +
                "id=" + id +
                ", wxName='" + wxName + '\'' +
                ", oppenId='" + oppenId + '\'' +
                ", sex='" + sex + '\'' +
                ", identity=" + identity +
                ", schoolId=" + schoolId +
                ", joinTime=" + joinTime +
                ", isVip=" + isVip +
                ", wxNum='" + wxNum + '\'' +
                ", vo_id='" + vo_id + '\'' +
                '}';
    }
}
