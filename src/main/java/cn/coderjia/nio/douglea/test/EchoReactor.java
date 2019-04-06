package cn.coderjia.nio.douglea.test;

import cn.coderjia.nio.douglea.reactor3.Acceptor;
import cn.coderjia.nio.douglea.reactor3.Reactor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author CoderJiA
 * @Description EchoReactor
 * @Date 6/4/19 下午5:35
 **/
public class EchoReactor extends Reactor {

    private static final int PORT = 9999;
    private static final long TIME_OUT = TimeUnit.MILLISECONDS.toMillis(10);

    private static final int SUB_REACTORS_SIZE = 2;
    private static final Reactor[] SUB_REACTORS = new Reactor[SUB_REACTORS_SIZE];
    private static final AtomicInteger NEXT_INDEX = new AtomicInteger(0);

    static {
        // 初始化子Reactor
        IntStream.range(0, SUB_REACTORS_SIZE).forEach(i -> SUB_REACTORS[i] = new EchoReactor(PORT, TIME_OUT, false));
    }

    public static Reactor nextSubReactor(){

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
    public Acceptor newAcceptor() {
        return new Acceptor(this.serverSocket);
    }


    public static void main(String[] args) {

        Reactor mainReactor = new EchoReactor(PORT, TIME_OUT, true);

        // 启动主Reactor
        new Thread(mainReactor).start();

        // 启动子Reactor
        IntStream.range(0, SUB_REACTORS_SIZE).forEach(i -> new Thread(SUB_REACTORS[i]).start());
    }

}
