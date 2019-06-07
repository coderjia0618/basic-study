package cn.coderjia.netty.sr.demo3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

/**
 * @Author CoderJiA
 * @Description Outbound2
 * @Date 7/6/2019 9:33 AM
 **/
public class Outbound2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Outbound2...");
        super.exceptionCaught(ctx, cause);
    }

}
