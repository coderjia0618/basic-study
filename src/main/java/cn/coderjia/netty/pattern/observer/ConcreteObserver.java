package cn.coderjia.netty.pattern.observer;

/**
 * @Author CoderJiA
 * @Description ConcreteObserver
 * @Date 15/6/2019 1:07 PM
 **/
public class ConcreteObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("message up: " + message);
    }

}
