package wall.pojo;

import java.util.List;

public class LoveParentVo extends LoveParent {

    private String vo_time; // 发表时间，前端展示
    private List<LoveSub> loveSubs; // 子表数据集合
    private String start_time; // 开始时间，用于封装查询条件
    private String end_time; // 结束时间，用于封装查询条件
    private LikeVo like; // 该表白的点赞情况


    public LikeVo getLike() {
        return like;
    }

    public LoveParentVo setLike(LikeVo like) {
        this.like = like;
        return this;
    }

    private String topicName; // 所属话题的名称


    public String getTopicName() {
        return topicName;
    }

    public LoveParentVo setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public String getStart_time() {
        return start_time;
    }

    public LoveParentVo setStart_time(String start_time) {
        this.start_time = start_time;
        return this;
    }

    public String getEnd_time() {
        return end_time;
    }

    public LoveParentVo setEnd_time(String end_time) {
        this.end_time = end_time;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public LoveParentVo setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    public List<LoveSub> getLoveSubs() {
        return loveSubs;
    }

    public LoveParentVo setLoveSubs(List<LoveSub> loveSubs) {
        this.loveSubs = loveSubs;
        return this;
    }

    @Override
    public String toString() {
        return "LoveParentVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                ", loveSubs=" + loveSubs +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                '}';
    }
}
