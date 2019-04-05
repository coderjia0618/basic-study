package cn.coderjia.nio.douglea.reactor.test;

import cn.coderjia.nio.douglea.reactor.Acceptor;
import cn.coderjia.nio.douglea.reactor.Reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.TimeUnit;

/**
 * @Author CoderJiA
 * @Description HelloReactor
 * @Date 5/4/19 下午5:01
 **/
public class HelloReactor extends Reactor {

    private static final int PORT = 9999;
    private static final long TIME_OUT = TimeUnit.MILLISECONDS.toMillis(10);

    public HelloReactor(int port, long timeout) throws IOException {
        super(port, timeout);
    }

    @Override
    public Acceptor newAcceptor(Selector selector) {
        return new Acceptor(selector, this.serverSocket);
    }

    public static void main(String[] args) throws IOException {
        new HelloReactor(PORT, TIME_OUT).run();
    }

}
