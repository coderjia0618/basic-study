package cn.coderjia.nio.douglea.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.TimeUnit;

/**
 * @Author CoderJiA
 * @Description EchoReactor
 * @Date 5/4/19 下午5:01
 **/
public class EchoReactor extends Reactor {

    private static final int PORT = 9999;
    private static final long TIME_OUT = TimeUnit.MILLISECONDS.toMillis(10);

    public EchoReactor(int port, long timeout) throws IOException {
        super(port, timeout);
    }

    @Override
    public Acceptor newAcceptor(Selector selector) {
        return new Acceptor(selector, this.serverSocket);
    }

    public static void main(String[] args) throws IOException {
        new EchoReactor(PORT, TIME_OUT).run();
    }

}
