package cn.coderjia.netty.pattern.observer;

import com.google.common.collect.Lists;

/**
 * @Author CoderJiA
 * @Description ConcreteObserverable
 * @Date 15/6/2019 12:49 PM
 **/
public class ConcreteObserverable extends Observerable {

    public ConcreteObserverable() {
        observers = Lists.newArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String message) {
        this.message = message;
        for (Observer observer: observers) {
            observer.update(message);
        }
    }

}
