package cn.coderjia.netty.pattern.chain;

/**
 * @Author CoderJiA
 * @Description Handle1
 * @Date 15/6/2019 3:40 PM
 **/
public class Handle1 extends AbstractHandler {

    @Override
    public void eventSpread() {
        System.out.println("Handle1 event spread...");
    }

}
