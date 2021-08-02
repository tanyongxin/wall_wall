package wall.pojo;

public class Like extends BasePojo {

    private Byte type; // 被点赞的帖子的类型，0 表示表白，1 表示提问，2 表示回复

    private Long targetId; // 被点赞的帖子的 id

    private Boolean isLike = true; // 是否取消点赞，true 为点赞，false 为取消点赞

    public Boolean getLike() {
        return isLike;
    }

    public Like setLike(Boolean like) {
        isLike = like;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public Like setType(Byte type) {
        this.type = type;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Like setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    @Override
    public String toString() {
        return "Like{" +
                super.toString() +
                "type=" + type +
                ", targetId=" + targetId +
                ", isLike=" + isLike +
                '}';
    }
}
