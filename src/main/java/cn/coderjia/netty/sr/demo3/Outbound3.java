package cn.coderjia.netty.sr.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @Author CoderJiA
 * @Description Outbound3
 * @Date 7/6/2019 9:33 AM
 **/
public class Outbound3 extends ChannelOutboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Outbound3...");
        super.exceptionCaught(ctx, cause);
    }

}
