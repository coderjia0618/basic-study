package cn.coderjia.nio.zerocopy.model2;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author CoderJiA
 * @Description TransferModel2Client
 * @Date 23/2/19 下午3:36
 **/
public class TransferModel2Client {

    private static final String HOST = "localhost";
    private static final int PORT = 8899;
    private static final String FILE_PATH = "/Users/coderjia/Documents/gradle-5.2.1-all.zip";

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(true);
        FileChannel fileChannel = new FileInputStream(FILE_PATH).getChannel();
        long start = System.currentTimeMillis();
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
        fileChannel.close();
    }

}
