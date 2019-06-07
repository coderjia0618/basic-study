package cn.coderjia.netty.sr.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author CoderJiA
 * @Description Outbound3
 * @Date 7/6/2019 9:33 AM
 **/
public class Outbound3 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("oubound3 write:" + msg);
        ctx.write(msg, promise);
    }

}
