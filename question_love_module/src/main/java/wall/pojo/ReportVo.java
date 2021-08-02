package wall.pojo;

public class ReportVo extends Report {

    private String vo_time; // 提交举报的时间

    public String getVo_time() {
        return vo_time;
    }

    public ReportVo setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    @Override
    public String toString() {
        return "ReportVo{" +
                super.toString() +
                "vo_time='" + vo_time + '\'' +
                '}';
    }
}
