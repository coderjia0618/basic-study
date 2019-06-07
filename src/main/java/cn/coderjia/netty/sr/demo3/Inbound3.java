package cn.coderjia.netty.sr.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author CoderJiA
 * @Description Outbound3
 * @Date 7/6/2019 9:33 AM
 **/
public class Inbound3 extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Inbound2...");
        super.exceptionCaught(ctx, cause);
    }

}
