package cn.coderjia.netty.pattern.strategy;

/**
 * @Author CoderJiA
 * @Description RolePubStrategy
 * @Date 14/6/2019 4:27 PM
 **/
public class RolePubStrategy implements PubStrategy {

    @Override
    public void pub(String key) {
        // get users by role key
        System.out.println("1.Get users by role key...");
        // foreach pub msg to users
        System.out.println("2.Foreach pub msg to users...");
    }

}
