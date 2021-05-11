package wall.pojo;

import java.util.List;

public class LoveParentVo extends LoveParent {

    private String vo_time; // 发表时间，前端展示
    private List<LoveSub> loveSubs;
    private String userName; // 发表人的用户名
    private String start_time; // 开始时间，用于封装查询条件
    private String end_time; // 结束时间，用于封装查询条件

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

    public String getUserName() {
        return userName;
    }

    public LoveParentVo setUserName(String userName) {
        this.userName = userName;
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
}
