package cn.coderjia.nio.douglea.reactor2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author CoderJiA
 * @Description Handler
 * @Date 5/4/19 下午4:25
 **/
public class Handler implements Runnable {

    private static final int MB = 1024 * 1024;

    protected final SocketChannel socket;
    protected final SelectionKey sk;
    protected final ByteBuffer input = ByteBuffer.allocate(MB);
    protected final ByteBuffer output = ByteBuffer.allocate(MB);

    private static final int READING = 0, SENDING = 1, PROCESSING = 3;
    private int state = READING;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Handler(Selector selector, SocketChannel socket) throws IOException {
        this.socket = socket;
        socket.configureBlocking(false);
        sk = socket.register(selector, SelectionKey.OP_READ);
        sk.attach(this);
    }

    @Override
    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            state = PROCESSING;
            EXECUTOR_SERVICE.execute(new Processer());
        }
        input.clear();
    }

    private void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }

    private void process() {
        System.out.println("Handler.process()...");
    }

    private boolean inputIsComplete() {
        return input.position() > 0;
    }

    private boolean outputIsComplete() {
        return !output.hasRemaining();
    }

    class Processer implements Runnable {
        public void run() {
            processAndHandOff();
        }
    }

    synchronized void processAndHandOff() {
        process();
        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE);
    }

}
