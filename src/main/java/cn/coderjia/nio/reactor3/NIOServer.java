package cn.coderjia.nio.reactor3;



import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @Author CoderJiA
 * @Description NIOServer
 * @Date 13/2/19 下午4:59
 **/
public class NIOServer {

    public static void main(String[] args) throws Exception{

        // 1.创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        // 2.创建Selector，并ServerSocketChannel注册OP_ACCEPT事件，接收连接。
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 3.创建processor
        int coreProcessorNum = Runtime.getRuntime().availableProcessors();
        Processor[] processors = new Processor[coreProcessorNum];
        IntStream.range(0, processors.length).forEach(i -> processors[i] = new Processor());

        // 4.开启轮询
        MutableInt index = new MutableInt(0);
        while (selector.select() > 0) {
            // 主Reactor监听连接
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                try {
                    if (selectionKey.isAcceptable()) {  // 接受事件就绪
                        // 获取serverSocketChannel
                        ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                        // 接收连接
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        Processor processor = processors[(index.getValue()) % coreProcessorNum];
                        addVal(coreProcessorNum, index);
                        processor.addSocketChannel(client);
                        processor.wakeup();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                selectionKeys.remove(selectionKey);
            });
        }

    }

    private static void addVal(int coreProcessorNum, MutableInt index) {
        if (index.getValue() > coreProcessorNum * 100) {
            index.setValue(0);
        } else {
            index.increment();
        }
    }

}
