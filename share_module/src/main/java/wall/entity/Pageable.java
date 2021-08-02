package wall.entity;

import java.util.List;

// 分页的实体类
public class Pageable<T,R> {

    private T entity; // 封装查询条件的实体类

    private int pageSize = 10; // 每页多少条

    private int pageNum = 1; // 当前页数

    private List<R> res; // 结果集

    private Long lastId; // 上一页最后一条记录的 id 值

    private Integer from; // 从第几条开始取数据

    public Integer getFrom() {
        return from;
    }

    public Pageable<T,R> setFrom(Integer from) {
        this.from = from;
        return this;
    }

    public Long getLastId() {
        return lastId;
    }

    public Pageable<T,R> setLastId(long lastId) {
        this.lastId = lastId;
        return this;
    }

    public T getEntity() {
        return entity;
    }

    public Pageable<T,R> setEntity(T entity) {
        this.entity = entity;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Pageable<T,R> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Pageable<T,R> setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public List<R> getRes() {
        return res;
    }

    public Pageable<T,R> setRes(List<R> res) {
        this.res = res;
        return this;
    }

    @Override
    public String toString() {
        return "Pageable{" +
                "entity=" + entity +
                ", pageSize=" + pageSize +
                ", res=" + res +
                ", lastId=" + lastId +
                '}';
    }
}
