package cn.coderjia.netty.sr.demo1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author CoderJiA
 * @Description Outbound3
 * @Date 7/6/2019 9:33 AM
 **/
public class Inbound3 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("at InBound3: " + msg);
        ctx.fireChannelRead(msg);
    }

}
