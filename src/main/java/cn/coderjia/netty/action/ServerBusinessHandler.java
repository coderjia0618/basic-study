package cn.coderjia.netty.action;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author CoderJiA
 * @Description ServerBusinessHandler
 * @Date 14/6/2019 7:01 AM
 **/
@ChannelHandler.Sharable
public class ServerBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static final ServerBusinessHandler INSTANCE = new ServerBusinessHandler();

    private static final ExecutorService executorService = Executors.newFixedThreadPool(24);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        ByteBuf data = Unpooled.directBuffer();
        data.writeBytes(msg);
        // 模拟耗时的操作
        executorService.submit(()->{
            Object result = getDataByDB(data);
            ctx.channel().writeAndFlush(result);
        });
    }

    protected Object getDataByDB(ByteBuf data) {

        int lvl = ThreadLocalRandom.current().nextInt(1, 1000);

        int sleep;
        if (lvl <= 900) {
            sleep = 1;
        } else if (lvl <= 950) {
            sleep = 10;
        } else if (lvl <= 990) {
            sleep = 100;
        } else {
            sleep = 1000;
        }

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data;
    }
}
