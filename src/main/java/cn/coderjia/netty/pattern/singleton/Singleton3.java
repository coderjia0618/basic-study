package cn.coderjia.netty.pattern.singleton;

/**
 * @Author CoderJiA
 * @Description Singleton3
 * @Date 14/6/2019 3:11 PM
 **/
public class Singleton3 {

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Singleton3 instance = new Singleton3();
    }

}
