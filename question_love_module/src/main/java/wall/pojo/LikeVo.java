package wall.pojo;

public class LikeVo extends Like {

    private String update_time; // 修改时间

    public String getUpdate_time() {
        return update_time;
    }

    public Like setUpdate_time(String update_time) {
        this.update_time = update_time;
        return this;
    }

    @Override
    public String toString() {
        return "LikeVo{" +
                super.toString() +
                "update_time='" + update_time + '\'' +
                '}';
    }
}
