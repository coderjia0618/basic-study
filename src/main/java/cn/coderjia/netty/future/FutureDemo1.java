package cn.coderjia.netty.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author CoderJiA
 * @Description FutureDemo1
 * @Date 3/3/19 下午1:32
 **/
public class FutureDemo1 {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public static void main(String[] args) throws Exception{
        // 创建FutureTask
        FutureTask<Integer> future = new FutureTask<>(FutureDemo1::strLen);
        // 执行FutureTask
        EXECUTOR_SERVICE.execute(future);
        // future.get()会阻塞的等待结果，
        System.out.println(future.get());
    }

    private static int strLen() throws InterruptedException {
        return "hello cj...".length();
    }

}
