package cn.coderjia.nio.zerocopy.model2;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author CoderJiA
 * @Description TransferModel2Client
 * @Date 23/2/19 下午3:36
 **/
public class TransferModel2Server {

    private static final int PORT = 8899;
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {

        InetSocketAddress address = new InetSocketAddress(PORT);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(MB);
        for (;;) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int readSize = 0;
            while (-1 != readSize) {
                readSize = socketChannel.read(byteBuffer);
                byteBuffer.rewind();
            }

        }

    }
}
