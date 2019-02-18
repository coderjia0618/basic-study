package cn.coderjia.netty.idlestate;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author CoderJiA
 * @Description TestIdleStateHandler
 * @Date 3/2/19 下午8:24
 **/
public class TestIdleStateHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            String evtType = null;
            switch (event.state()) {
                case READER_IDLE:
                    evtType = "读空闲";
                    break;
                case WRITER_IDLE:
                    evtType = "写空闲";
                    break;
                case ALL_IDLE:
                    evtType = "读写空闲";
                    break;
            }
            Channel curChannel = ctx.channel();
            System.out.println(ctx.channel().remoteAddress() + "超时事件：" + evtType);
            curChannel.closeFuture();
        }
    }
}
