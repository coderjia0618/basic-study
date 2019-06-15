package cn.coderjia.netty.pattern.decorator;

import cn.coderjia.netty.pattern.strategy.PubStrategy;

/**
 * @Author CoderJiA
 * @Description StrategyContextDecorator
 * @Date 15/6/2019 10:39 AM
 **/
public class StrategyContextDecorator implements PubStrategy {

    private PubStrategy pubStrategy;

    public StrategyContextDecorator(PubStrategy pubStrategy) {
        this.pubStrategy = pubStrategy;
    }

    @Override
    public void pub(String key) {
        pubStrategy.pub(key);
    }

}
