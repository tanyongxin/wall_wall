package wall.pojo;

public class UserStub {

    private String avatars; // 用户头像
    private String wxName; // 用户名

    public String getAvatars() {
        return avatars;
    }

    public UserStub setAvatars(String avatars) {
        this.avatars = avatars;
        return this;
    }

    public String getWxName() {
        return wxName;
    }

    public UserStub setWxName(String wxName) {
        this.wxName = wxName;
        return this;
    }

    @Override
    public String toString() {
        return "UserStub{" +
                "avatars='" + avatars + '\'' +
                ", wxName='" + wxName + '\'' +
                '}';
    }
}
