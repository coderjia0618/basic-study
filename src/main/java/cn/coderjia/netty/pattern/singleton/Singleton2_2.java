package cn.coderjia.netty.pattern.singleton;

/**
 * @Author CoderJiA
 * @Description Singleton2_2
 * @Date 14/6/2019 3:01 PM
 **/
public class Singleton2_2 {

    private Singleton2_2() {}

    private static Singleton2_2 instance = null;

    public static Singleton2_2 getInstance() {
        if (null == instance) {
            synchronized(Singleton2_2.class) {
                if (null == instance) {
                    instance = new Singleton2_2();
                }
            }
        }
        return instance;
    }


}
