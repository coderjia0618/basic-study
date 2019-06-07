package cn.coderjia.netty.sr.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author CoderJiA
 * @Description Outbound1
 * @Date 7/6/2019 9:32 AM
 **/
public class Inbound1 extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Inbound1...");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        throw new RuntimeException("cj test throw caught...");
    }
}
