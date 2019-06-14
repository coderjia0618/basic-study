package cn.coderjia.netty.sr.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * @Author CoderJiA
 * @Description Demo1
 * @Date 12/6/2019 5:30 PM
 **/
public class Demo1 extends FastThreadLocalThread {


    private static final FastThreadLocal<Object> THREAD_LOCAL1 = new FastThreadLocal<Object>();

    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();



    public static void main(String[] args) {
//        THREAD_LOCAL1.get();
//        THREAD_LOCAL2.get();


        Object o = THREAD_LOCAL.get();
        System.out.println(o);
    }


}
