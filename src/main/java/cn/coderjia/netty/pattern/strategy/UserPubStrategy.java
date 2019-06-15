package cn.coderjia.netty.pattern.strategy;

/**
 * @Author CoderJiA
 * @Description UserPubStrategy
 * @Date 14/6/2019 4:25 PM
 **/
public class UserPubStrategy implements PubStrategy {

    @Override
    public void pub(String key) {
        // find user by key
        System.out.println("1.Get user by key...");
        // pub msg to user
        System.out.println("2.Pub msg to user...");
    }

}
