package wall.pojo;

public class ReplyVo extends Reply {

    private String vo_time; // 发表时间，用于前端展示

    private LikeVo like; // 该回复的点赞情况

    private Boolean haveSub; // 该回复是否有回复

    public Boolean getHaveSub() {
        return haveSub;
    }

    public ReplyVo setHaveSub(Boolean haveSub) {
        this.haveSub = haveSub;
        return this;
    }

    public LikeVo getLike() {
        return like;
    }

    public ReplyVo setLike(LikeVo like) {
        this.like = like;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public ReplyVo setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    @Override
    public String toString() {
        return "ReplyVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                ", like=" + like +
                ", haveSub=" + haveSub +
                '}';
    }
}
