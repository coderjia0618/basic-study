package cn.coderjia.netty.sr.demo3;

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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Outbound1...");
        super.exceptionCaught(ctx, cause);
    }

}
