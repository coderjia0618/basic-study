package cn.coderjia.nio.zerocopy.moel1;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author CoderJiA
 * @Description TransferModel1Server
 * @Date 23/2/19 下午3:01
 **/
public class TransferModel1Client {

    private static final String HOST = "localhost";
    private static final int PORT = 8899;
    private static final String FILE_PATH = "/Users/coderjia/Documents/gradle-5.2.1-all.zip";
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(HOST, PORT);
        InputStream input = new FileInputStream(FILE_PATH);
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[MB];
        long start = System.currentTimeMillis();
        int len;
        while ((len = input.read(bytes)) != -1) {
            output.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
        output.close();
        input.close();
        socket.close();
    }
}
