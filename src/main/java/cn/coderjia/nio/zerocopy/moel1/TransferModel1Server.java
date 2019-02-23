package cn.coderjia.nio.zerocopy.moel1;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author CoderJiA
 * @Description TransferModel1Server
 * @Date 23/2/19 下午3:01
 **/
public class TransferModel1Server {

    private static final int PORT = 8899;
    private static final int MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(PORT);
        for (;;) {
            Socket socket = serverSocket.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            byte[] bytes = new byte[MB];
            for (;;) {
                int readSize = input.read(bytes, 0, MB);
                if (-1 == readSize) {
                    break;
                }
            }
        }
    }
}
