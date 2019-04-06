package cn.coderjia.nio.douglea.reactor3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Objects;
import java.util.Set;

/**
 * @Author CoderJiA
 * @Description Reactor
 * @Date 5/4/19 下午2:25
 **/
public abstract class Reactor implements Runnable {

    protected Selector selector;
    protected ServerSocketChannel serverSocket;

    protected final int port;
    protected final long timeout;
    protected final boolean isMainReactor;

    public Reactor(int port, long timeout, boolean isMainReactor) {
        this.port = port;
        this.timeout = timeout;
        this.isMainReactor = isMainReactor;
    }

    @Override
    public void run() {
        try {
            init();
            while (!Thread.interrupted()) {
                if (selector.select(timeout) > 0) {
                    System.out.println("isMainReactor:" + isMainReactor);
                    Set<SelectionKey> selected = selector.selectedKeys();
                    selected.forEach(sk -> {
                        dispatch(sk);
                        selected.remove(sk);
                    });
                    selected.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        selector = Selector.open();
        if (isMainReactor) {
            serverSocket = ServerSocketChannel.open();
            serverSocket
                    .socket()
                    .bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(newAcceptor());
        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable r = (Runnable)(sk.attachment());
        if (Objects.nonNull(r)) {
            r.run();
        }
    }

    public abstract Acceptor newAcceptor();

}
