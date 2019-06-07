package cn.coderjia.netty.sr.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @Author CoderJiA
 * @Description Outbound2
 * @Date 7/6/2019 9:33 AM
 **/
public class Outbound2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("oubound2 write:" + msg);
        ctx.write(msg, promise);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().schedule(()-> {
            ctx.pipeline().write("hello cj...");
        }, 5, TimeUnit.SECONDS);
    }

}
