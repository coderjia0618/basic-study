package cn.coderjia.netty.action;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author CoderJiA
 * @Description ClientBusinessHandler
 * @Date 14/6/2019 7:18 AM
 **/
@ChannelHandler.Sharable
public class ClientBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static final ClientBusinessHandler INSTANCE = new ClientBusinessHandler();

    private static AtomicLong beginTime = new AtomicLong(0);
    private static AtomicLong totalRequest = new AtomicLong(0);
    private static AtomicLong totalResponseTime = new AtomicLong(0);

    private ClientBusinessHandler(){}

    private static final Thread CALULATE_THREAD = new Thread(()->{
        for (;;) {
            long duratoin = System.currentTimeMillis() - beginTime.get();
            System.out.println(
                    "QPS:" + 1000 * totalRequest.get() / duratoin
                    + ","
                    + "Avg response Time:" + totalResponseTime.get() / totalRequest.get() * 1.0
            );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        totalResponseTime.addAndGet(System.currentTimeMillis() - msg.readLong());
        totalRequest.incrementAndGet();
        if (beginTime.compareAndSet(0, System.currentTimeMillis())) {
            CALULATE_THREAD.start();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.executor().scheduleAtFixedRate(() ->{
            ByteBuf byteBuf = ctx.alloc().ioBuffer();
            byteBuf.writeLong(System.currentTimeMillis());
            ctx.channel().writeAndFlush(byteBuf);
        }, 0, 1, TimeUnit.SECONDS);
    }
}
