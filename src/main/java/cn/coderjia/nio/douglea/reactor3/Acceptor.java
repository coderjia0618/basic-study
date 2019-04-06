package cn.coderjia.nio.douglea.reactor3;

import cn.coderjia.nio.douglea.reactor.Handler;
import cn.coderjia.nio.douglea.test.EchoReactor;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * @Author CoderJiA
 * @Description Acceptor
 * @Date 5/4/19 下午2:58
 **/
public class Acceptor implements Runnable {

    private final ServerSocketChannel serverSocket;

    public Acceptor(ServerSocketChannel serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            SocketChannel socket = serverSocket.accept();
            if (Objects.nonNull(socket)) {
                new Handler(EchoReactor.nextSubReactor().selector, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
