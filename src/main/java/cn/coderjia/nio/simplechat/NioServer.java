package cn.coderjia.nio.simplechat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @Author CoderJiA
 * @Description NioServer
 * @Date 9/2/19 上午9:03
 **/
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                SocketChannel client;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        String curKey = "【“ "+ UUID.randomUUID().toString() + "】";
                        clientMap.put(curKey, client);
                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel)selectionKey.channel();
                        ByteBuffer readBuf = ByteBuffer.allocate(4);
                        int count = client.read(readBuf);
                        if (count > 0 ) {
                            readBuf.flip();
                            Charset charset = Charset.forName("utf-8");
                            String receivedMsg = String.valueOf(charset.decode(readBuf).array());
                            System.out.println("【client:" + client + "】" + receivedMsg);
                            clientMap.forEach((uid, c) -> {
                                ByteBuffer writerBuffer = ByteBuffer.allocate(1024);
                                if (c == client) {
                                    writerBuffer.put(("【Me】" + receivedMsg).getBytes());
                                } else {
                                    writerBuffer.put(("【Broadcast】" + receivedMsg).getBytes());
                                }
                                writerBuffer.flip();
                                try {
                                    c.write(writerBuffer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                selectionKeys.remove(selectionKey);
            });
        }

    }

}
