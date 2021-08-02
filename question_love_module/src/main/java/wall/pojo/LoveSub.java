package wall.pojo;

public class LoveSub {

    private Long id;

    private String image = ""; // 图片地址

    private Long parentId; // 表白主表的 id

    private Byte type; // 类型，两种，0 表示图片，1 表示文字

    private String content = ""; // 内容

    private String vo_id;

    public String getVo_id() {
        return vo_id;
    }

    public LoveSub setVo_id(String vo_id) {
        this.vo_id = vo_id;
        this.id = Long.parseLong(vo_id);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.vo_id = id + "";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "LoveSub{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", parentId=" + parentId +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
