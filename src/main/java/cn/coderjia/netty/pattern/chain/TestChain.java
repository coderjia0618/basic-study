package cn.coderjia.netty.pattern.chain;

/**
 * @Author CoderJiA
 * @Description TestChain
 * @Date 15/6/2019 3:52 PM
 **/
public class TestChain {

    public static void main(String[] args) {

        AbstractHandler h1 = new Handle1();
        AbstractHandler h2 = new Handle2();

        h1.setNext(h2);

        h1.spread();
    }

}
