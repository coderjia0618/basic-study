package cn.coderjia.nio.douglea.reactor;

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
public abstract class Reactor implements Runnable{


    protected final Selector selector;
    protected final ServerSocketChannel serverSocket;

    protected final long port;
    protected final long timeout;

    public Reactor(int port, long timeout) throws IOException {
        this.port = port;
        this.timeout = timeout;
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket
                .socket()
                .bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(newAcceptor(selector));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (selector.select(timeout) > 0) {
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

    private void dispatch(SelectionKey sk) {
        Runnable r = (Runnable)(sk.attachment());
        if (Objects.nonNull(r)) {
            r.run();
        }
    }

    public abstract Acceptor newAcceptor(Selector selector);

}
