package wall.util;

public class Res<T> {

    private StateCode state; // 状态码
    private T result;  // 当需要向小程序端返回数据，才设置该值


    public Res(){}

    public Res(StateCode state){
        this.state = state;
    }


    public Res(StateCode state, T result) {
        this.state = state;
        this.result = result;
    }

    public StateCode getState() {
        return state;
    }

    public Res<T> setState(StateCode state) {
        this.state = state;
        return this;
    }

    public T getResult() {
        return result;
    }

    public Res<T> setResult(T result) {
        this.result = result;
        return this;
    }
}
