package wall.pojo;

public class LoginPojo {

    private String avatars; // 用户头像
    private String code;
    private String wxName;

    public String getAvatars() {
        return avatars;
    }

    public LoginPojo setAvatars(String avatars) {
        this.avatars = avatars;
        return this;
    }

    public String getCode() {
        return code;
    }

    public LoginPojo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getWxName() {
        return wxName;
    }

    public LoginPojo setWxName(String wxName) {
        this.wxName = wxName;
        return this;
    }
}
