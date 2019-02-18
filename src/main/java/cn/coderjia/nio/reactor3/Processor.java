package cn.coderjia.nio.reactor3;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CoderJiA
 * @Description Processor
 * @Date 16/2/19 下午6:00
 **/
public class Processor {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private Selector selector;

    public Processor() {
        try {
            this.selector = Selector.open();
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSocketChannel(SocketChannel socketChannel) throws ClosedChannelException {
        System.out.println("processor addSocketChannel...");
        socketChannel.register(this.selector, SelectionKey.OP_READ);
    }

    public void wakeup() {
        System.out.println("processor wakeup...");
        this.selector.wakeup();
    }

    private void start() {
        System.out.println("processor start...");
        EXECUTOR_SERVICE.execute(this::run);
    }

    private void run() {
        System.out.println("processor run...");
        try {
            while (true) {
                if (selector.select(100) <= 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    try {
                        if (selectionKey.isReadable()) {
                            // 获取socketChannel
                            SocketChannel client = (SocketChannel) selectionKey.channel();
                            // 创建buffer,并将获取socketChannel中的数据读入到buffer中
                            ByteBuffer readBuf = ByteBuffer.allocate(1024);
                            int readCount = client.read(readBuf);
                            if (readCount <= 0) {
                                return;
                            }
                            Charset charset = Charset.forName("UTF-8");
                            readBuf.flip();
                            System.out.println(charset.decode(readBuf).array());
                        }
                        selectionKeys.remove(selectionKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
