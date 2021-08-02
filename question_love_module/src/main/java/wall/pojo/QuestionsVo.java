package wall.pojo;

import java.util.List;

public class QuestionsVo extends Questions {

    private String vo_time; // 发表时间，用于前端展示

    private String start_time; // 开始时间，用于查询，格式为：2021-05-10 00:00:00
    private String end_time; // 结束时间，用于查询，格式为：2021-05-10 00:00:00

    private String topicName; // 所属话题的名称

    private List<ReplyVo> replyVos; // 提问对应的回复

    private LikeVo like; // 该提问的点赞情况

    public LikeVo getLike() {
        return like;
    }

    public QuestionsVo setLike(LikeVo like) {
        this.like = like;
        return this;
    }

    public List<ReplyVo> getReplyVos() {
        return replyVos;
    }

    public QuestionsVo setReplyVos(List<ReplyVo> replyVos) {
        this.replyVos = replyVos;
        return this;
    }

    public String getStart_time() {
        return start_time;
    }

    public QuestionsVo setStart_time(String start_time) {
        this.start_time = start_time;
        return this;
    }

    public String getEnd_time() {
        return end_time;
    }

    public QuestionsVo setEnd_time(String end_time) {
        this.end_time = end_time;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public QuestionsVo setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public QuestionsVo setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    @Override
    public String toString() {
        return "QuestionsVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
