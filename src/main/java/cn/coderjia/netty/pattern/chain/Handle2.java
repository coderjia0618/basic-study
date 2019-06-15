package cn.coderjia.netty.pattern.chain;

/**
 * @Author CoderJiA
 * @Description Handle1
 * @Date 15/6/2019 3:40 PM
 **/
public class Handle2 extends AbstractHandler {

    @Override
    public void eventSpread() {
        System.out.println("Handle2 event spread...");
    }

}
