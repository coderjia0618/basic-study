package cn.coderjia.netty.pattern.observer;

import java.util.List;

/**
 * @Author CoderJiA
 * @Description Observerable
 * @Date 15/6/2019 12:12 PM
 **/
public abstract class Observerable {

    protected List<Observer> observers;
    protected String message;

    /**
     * 添加一个观察者
     * @param observer 观察者对象
     */
    abstract public void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     * @param observer 观察者对象
     */
    abstract public void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    abstract public void notifyObserver(String message);

}
