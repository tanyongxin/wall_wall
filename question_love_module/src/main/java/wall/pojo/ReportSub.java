package wall.pojo;

// 举报的子表对应的实体类
public class ReportSub extends BasePojo {

    private Long parentId; // 举报主表的 id

    private Byte auditRes = 0; // 该用户对于该举报的审核意见，0 表示未审核，1 表示已审核，同意封贴，2 表示已审核，不同意封贴

    private Byte isValid = 1; // 是否有效，如果某个用户超过了一定的时间还不对举报进行审核，则该用户对本次举报的审核失效。1 表示有效，0 表示失效。

    private String vo_time; // 最后修改时间

    private ReportVo report;

    public ReportVo getReport() {
        return report;
    }

    public ReportSub setReport(ReportVo report) {
        this.report = report;
        return this;
    }

    public String getVo_time() {
        return vo_time;
    }

    public ReportSub setVo_time(String vo_time) {
        this.vo_time = vo_time;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public ReportSub setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Byte getAuditRes() {
        return auditRes;
    }

    public ReportSub setAuditRes(Byte auditRes) {
        this.auditRes = auditRes;
        return this;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public ReportSub setIsValid(Byte isValid) {
        this.isValid = isValid;
        return this;
    }

    @Override
    public String toString() {
        return "ReportSub{" +
                super.toString() +
                "parentId=" + parentId +
                ", auditRes=" + auditRes +
                ", isValid=" + isValid +
                ", vo_time='" + vo_time + '\'' +
                ", report=" + report +
                '}';
    }
}
