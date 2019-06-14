package cn.coderjia.netty.action;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.util.stream.IntStream;

import static cn.coderjia.netty.action.Consts.PORT;
import static cn.coderjia.netty.action.Consts.SERVER_IP;

/**
 * @Author CoderJiA
 * @Description Client
 * @Date 14/6/2019 7:38 AM
 **/
public class Client {

    public static void main(String[] args) {
        new Client().start();
    }

    private void start(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new FixedLengthFrameDecoder(Long.BYTES));
                        ch.pipeline().addLast(ClientBusinessHandler.INSTANCE);
                    }
                });
        IntStream.range(0, 1000).forEach(i -> {
            try {
                bootstrap.connect(SERVER_IP, PORT).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
