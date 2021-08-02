package wall.pojo;

// 举报的主表对应的实体类
public class Report extends BasePojo {

    private Byte type; // 0 表示表白，1 表示提问，2 表示回复

    private Long targetId;

    private String image;

    private String describe;

    private Byte auditRes = 0; // 最终审核结果，0 表示正在审核；1 表示已审核，同意封贴；2 表示已审核，不同意封贴。如果该字段不为 0，说明该举报已完成审核

    private Integer auditSize = 3; // 本次举报的审核人数

    private Integer auditRealSize = 0; // 本次举报的实际审核人数，该值小于等于 audit_size。


    public Integer getAuditSize() {
        return auditSize;
    }

    public Report setAuditSize(Integer auditSize) {
        if ( auditSize >= 10 )
            auditSize = 3;
        this.auditSize = auditSize;
        return this;
    }

    public Integer getAuditRealSize() {
        return auditRealSize ;
    }

    public Report setAuditRealSize(Integer auditRealSize) {
        this.auditRealSize = auditRealSize;
        return this;
    }

    public Byte getType() {
        return type;
    }

    public Report setType(Byte type) {
        this.type = type;
        return this;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Report setTargetId(Long targetId) {
        this.targetId = targetId;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Report setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescribe() {
        return describe;
    }

    public Report setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public Byte getAuditRes() {
        return auditRes;
    }

    public Report setAuditRes(Byte auditRes) {
        this.auditRes = auditRes;
        return this;
    }

    @Override
    public String toString() {
        return "Report{" +
                super.toString() +
                "type=" + type +
                ", targetId=" + targetId +
                ", image='" + image + '\'' +
                ", describe='" + describe + '\'' +
                ", auditRes=" + auditRes +
                ", auditSize=" + auditSize +
                ", auditRealSize=" + auditRealSize +
                '}';
    }
}
