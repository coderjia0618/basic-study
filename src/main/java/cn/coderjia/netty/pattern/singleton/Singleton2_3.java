package cn.coderjia.netty.pattern.singleton;

/**
 * @Author CoderJiA
 * @Description Singleton2_3
 * @Date 14/6/2019 3:09 PM
 **/
public class Singleton2_3 {

    private Singleton2_3() {}

    // 防止指令重排
    private static volatile Singleton2_3 instance = null;

    public static Singleton2_3 getInstance() {
        if (null == instance) {
            synchronized(Singleton2_3.class) {
                if (null == instance) {
                    instance = new Singleton2_3();
                }
            }
        }
        return instance;
    }

}
