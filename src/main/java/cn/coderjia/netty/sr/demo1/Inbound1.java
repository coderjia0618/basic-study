package cn.coderjia.netty.sr.demo1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author CoderJiA
 * @Description Outbound1
 * @Date 7/6/2019 9:32 AM
 **/
public class Inbound1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("at InBound1: " + msg);
        ctx.channel().pipeline().write(msg);
    }

}
