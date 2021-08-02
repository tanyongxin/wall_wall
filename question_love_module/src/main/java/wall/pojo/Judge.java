package wall.pojo;

// 评审员实体类
/*

如果评审员在指定时间内完成审核，则 complete 在原来的基础上加 1。如果评审员未在指定时间内完成审核，则 complete 不变
每次分配审核任务给评审员时，unfinished 和 currTask 都会加一，如果评审员未在指定时间内完成审核，则 unfinished 不变，如果评审员在指定时间内完成审核，则 unfinished 在原来的基础上减 1
只要评审员完成了评审任务，currTask 都会减一，不管是在指定时间内完成还是未在指定时间内完成
 */
public class Judge extends BasePojo {

    private Boolean isJudge = false; // 是否成为评审员

    private Integer complete = 0; // 完成评审的次数。

    private Integer unfinished = 0; // 未完成评审的次数。

    private Integer currTask = 0; // 当前需要完成的评审数

    private String reason = "wait audit"; // 原因，该字段主要是当某个用户被取消评审资格后填写。初始时为 wait audit，审核通过后为 success.

    public Boolean getJudge() {
        return isJudge;
    }

    public Judge setJudge(Boolean judge) {
        isJudge = judge;
        return this;
    }

    public Integer getComplete() {
        return complete;
    }

    public Judge setComplete(Integer complete) {
        this.complete = complete;
        return this;
    }

    public Integer getUnfinished() {
        return unfinished;
    }

    public Judge setUnfinished(Integer unfinished) {
        this.unfinished = unfinished;
        return this;
    }

    public Integer getCurrTask() {
        return currTask;
    }

    public Judge setCurrTask(Integer currTask) {
        this.currTask = currTask;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Judge setReason(String reason) {
        this.reason = reason;
        return this;
    }

    @Override
    public String toString() {
        return "Judge{" +
                super.toString() +
                "isJudge=" + isJudge +
                ", complete=" + complete +
                ", unfinished=" + unfinished +
                ", currTask=" + currTask +
                ", reason='" + reason + '\'' +
                '}';
    }
}
