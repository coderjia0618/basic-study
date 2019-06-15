package cn.coderjia.netty.pattern.strategy;

/**
 * @Author CoderJiA
 * @Description RolePubStrategy
 * @Date 14/6/2019 4:27 PM
 **/
public class StrategyContext {

    private PubStrategy strategy;

    public StrategyContext(PubStrategy strategy) {
        this.strategy = strategy;
    }

    public void pub(String key) {
        strategy.pub(key);
    }

    public static void main(String[] args) {
        int[] rets = new int[]{1,2};
        // int[] y= new int[]{1,2,3,4,5};
        new StrategyContext(new UserPubStrategy()).pub("key");
        new StrategyContext(new RolePubStrategy()).pub("key");
    }

}
