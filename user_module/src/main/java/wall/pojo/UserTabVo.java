package wall.pojo;

public class UserTabVo extends UserTab {

    private String vo_join_time;
    private String session_key;

    public String getVo_join_time() {
        return vo_join_time;
    }

    public UserTabVo setVo_join_time(String vo_join_time) {
        this.vo_join_time = vo_join_time;
        return this;
    }

    public String getSession_key() {
        return session_key;
    }

    public UserTabVo setSession_key(String session_key) {
        this.session_key = session_key;
        return this;
    }
}
