package wall.pojo;


// 用户分享实体类。用户分享到朋友圈时，需要插入一条记录到表中，该实体类对应的表为 user_share
public class UserShare extends BasePojo{

    private Long userId; // 分享的用户 id

    private Integer time; // 分享时间

    private String requestUrl; // 调用微信 api 接口时的 url

    private String page; // 分享的小程序页面

    private String vo_time; // 分享时间，用于前端展示

    private String codeUrl = "none"; // 小程序码的地址，用户获取到小程序码后可以上传到服务端，该字段就是图片的地址

    private String vo_userId;

    public String getVo_userId() {
        return vo_userId;
    }

    public UserShare setVo_userId(String vo_userId) {
        this.vo_userId = vo_userId;
        this.userId = Long.parseLong(vo_userId);
        return this;
    }

    public String getVo_id() {
        return vo_id;
    }

    public UserShare setVo_id(String vo_id) {
        this.vo_id = vo_id;
        this.id = Long.parseLong(vo_id);
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserShare setId(Long id) {
        this.id = id;
        this.vo_id = id + "";
        return this;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public UserShare setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public UserShare setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }



    public Long getUserId() {
        return userId;
    }

    public UserShare setUserId(Long userId) {
        this.userId = userId;
        this.vo_userId = userId + "";
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public UserShare setTime(Integer time) {
        this.time = time;
        return this;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public UserShare setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public String getPage() {
        return page;
    }

    public UserShare setPage(String page) {
        this.page = page;
        return this;
    }
}
