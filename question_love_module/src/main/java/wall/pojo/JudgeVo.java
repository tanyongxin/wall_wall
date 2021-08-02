package wall.pojo;

public class JudgeVo extends Judge {

    private String vo_time; // 申请时间，用于前端展示

    public String getVo_time() {
        return vo_time;
    }

    public Judge setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    @Override
    public String toString() {
        return "JudgeVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                '}';
    }
}
