package wall.pojo;

public class BasePojo {

    private Long id;

    private Integer time;

    private Long userId;

    private String vo_id;

    private String vo_userId;

    private String userName; // 发表人的用户名

    private String avatars; // 发表人头像

    public String getUserName() {
        return userName;
    }

    public BasePojo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getAvatars() {
        return avatars;
    }

    public BasePojo setAvatars(String avatars) {
        this.avatars = avatars;
        return this;
    }

    public String getVo_id() {
        return vo_id;
    }

    public BasePojo setVo_id(String vo_id) {
        this.vo_id = vo_id;
        this.id = Long.parseLong(vo_id);
        return this;
    }

    public String getVo_userId() {
        return vo_userId;
    }

    public BasePojo setVo_userId(String vo_userId) {
        this.vo_userId = vo_userId;
        this.userId = Long.parseLong(vo_userId);
        return this;
    }

    public Long getId() {
        return id;
    }

    public BasePojo setId(Long id) {
        this.id = id;
        this.vo_id = id + "";
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public BasePojo setTime(Integer time) {
        this.time = time;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public BasePojo setUserId(Long userId) {
        this.userId = userId;
        this.vo_userId = userId + "";
        return this;
    }

    @Override
    public String toString() {
        return "BasePojo{" +
                "id=" + id +
                ", time=" + time +
                ", userId=" + userId +
                ", vo_id='" + vo_id + '\'' +
                ", vo_userId='" + vo_userId + '\'' +
                ", userName='" + userName + '\'' +
                ", avatars='" + avatars + '\'' +
                '}';
    }
}
