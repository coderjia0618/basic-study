package cn.coderjia.nio.reactor2;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CoderJiA
 * @Description Processor
 * @Date 16/2/19 下午12:11
 **/
public class Processor {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(16);

    public void process(SelectionKey selectionKey) {
        EXECUTOR_SERVICE.execute(() -> {
            try {
                // 获取socketChannel
                SocketChannel client = (SocketChannel) selectionKey.channel();
                // 创建buffer,并将获取socketChannel中的数据读入到buffer中
                ByteBuffer readBuf = ByteBuffer.allocate(1024);
                int readCount = client.read(readBuf);
                if (readCount <= 0) {
                    return;
                }
                Charset charset = Charset.forName(StandardCharsets.UTF_8.name());
                readBuf.flip();
                System.out.println(charset.decode(readBuf).array());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
