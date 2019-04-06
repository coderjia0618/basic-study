package cn.coderjia.nio.douglea.test;

import cn.coderjia.nio.douglea.reactor3.Acceptor3;
import cn.coderjia.nio.douglea.reactor3.Reactor3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author CoderJiA
 * @Description EchoReactor
 * @Date 6/4/19 下午5:35
 **/
public class EchoReactor extends Reactor3 {

    private static final int PORT = 9999;
    private static final long TIME_OUT = TimeUnit.MILLISECONDS.toMillis(10);

    private static final int SUB_REACTORS_SIZE = 2;
    private static final Reactor3[] SUB_REACTORS = new Reactor3[SUB_REACTORS_SIZE];
    private static final AtomicInteger NEXT_INDEX = new AtomicInteger(0);

    static {
        // 初始化子Reactor
        IntStream.range(0, SUB_REACTORS_SIZE).forEach(i -> SUB_REACTORS[i] = new EchoReactor(PORT, TIME_OUT, false));
    }

    public static Reactor3 nextSubReactor(){

        int curIdx = NEXT_INDEX.getAndIncrement();

        if(curIdx >= SUB_REACTORS_SIZE){
            NEXT_INDEX.set(0);
            curIdx = 0;
        }
        return SUB_REACTORS[(curIdx % SUB_REACTORS_SIZE)];
    }

    public EchoReactor(int port, long timeout, boolean isMainReactor) {
        super(port, timeout, isMainReactor);
    }

    @Override
    public Acceptor3 newAcceptor() {
        return new Acceptor3(this.serverSocket);
    }


    public static void main(String[] args) {

        Reactor3 mainReactor = new EchoReactor(PORT, TIME_OUT, true);

        // 启动主Reactor
        new Thread(mainReactor).start();

        // 启动子Reactor
        IntStream.range(0, SUB_REACTORS_SIZE).forEach(i -> new Thread(SUB_REACTORS[i]).start());
    }

}
