package cn.coderjia.netty.sr.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author CoderJiA
 * @Description Outbound1
 * @Date 7/6/2019 9:32 AM
 **/
public class Outbound1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("oubound1 write:" + msg);
        ctx.write(msg, promise);
    }

}
