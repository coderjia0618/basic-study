package cn.coderjia.nio.douglea.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * @Author CoderJiA
 * @Description Acceptor
 * @Date 5/4/19 下午2:58
 **/
public class Acceptor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    public Acceptor(Selector selector, ServerSocketChannel serverSocket) {
        this.selector = selector;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            SocketChannel socket = serverSocket.accept();
            if (Objects.nonNull(socket)) {
                new Handler(selector, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
