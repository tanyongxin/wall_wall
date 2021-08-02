package wall.pojo;

public class Feedback extends BasePojo {

    private String suggest;

    private String vo_time;

    public String getVo_time() {
        return vo_time;
    }

    public Feedback setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }

    @Override
    public String toString() {
        return " Feedback{" +
                super.toString() +
                " suggest='" + suggest + '\'' +
                ", vo_time='" + vo_time + '\'' +
                '}';
    }
}
