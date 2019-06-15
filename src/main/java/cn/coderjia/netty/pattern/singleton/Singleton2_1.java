package cn.coderjia.netty.pattern.singleton;

/**
 * @Author CoderJiA
 * @Description Singleton2_1
 * @Date 14/6/2019 3:01 PM
 **/
public class Singleton2_1 {

    private Singleton2_1() {}

    private static Singleton2_1 instance = null;

    public static Singleton2_1 getInstance() {
        if (null == instance) {
            instance = new Singleton2_1();
        }
        return instance;
    }


}
