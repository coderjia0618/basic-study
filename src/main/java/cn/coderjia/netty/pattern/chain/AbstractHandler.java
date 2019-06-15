package cn.coderjia.netty.pattern.chain;

/**
 * @Author CoderJiA
 * @Description AbstractHandler
 * @Date 15/6/2019 3:38 PM
 **/
public abstract class AbstractHandler {

    private AbstractHandler next;

    abstract protected void eventSpread();

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public void spread() {
        eventSpread();
        // 向下传播
        if (null != next) {
            next.eventSpread();
        }
    }

}
