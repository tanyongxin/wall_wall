package wall.pojo;

public class QuestionsVo extends Questions {

    private String vo_time; // 发表时间，用于前端展示

    private String start_time; // 开始时间，用于查询，格式为：2021-05-10 00:00:00
    private String end_time; // 结束时间，用于查询，格式为：2021-05-10 00:00:00

    private String userName; // 发表提问的用户的用户名

    private String topicName; // 所属话题的名称


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

    public String getUserName() {
        return userName;
    }

    public QuestionsVo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getTopicName() {
        return topicName;
    }

    public QuestionsVo setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }
}
