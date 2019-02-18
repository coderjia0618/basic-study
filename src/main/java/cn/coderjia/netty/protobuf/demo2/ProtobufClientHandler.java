package cn.coderjia.netty.protobuf.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author CoderJiA
 * @Description ProtobufClientHandler
 * @Date 6/2/19 下午1:47
 **/
public class ProtobufClientHandler extends SimpleChannelInboundHandler<OuterMessage.MyMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OuterMessage.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        OuterMessage.MyMessage myMessage;
        if (0 == randomInt) {
            myMessage = OuterMessage.MyMessage.newBuilder()
                    .setDataType(OuterMessage.MyMessage.DataType.PersonType)
                    .setPerson(
                            OuterMessage.Person.newBuilder()
                                    .setName("cj")
                                    .setAge(25)
                                    .setAddress("sy")
                                    .build()
                    )
                    .build();
        } else if (1 == randomInt) {
            myMessage = OuterMessage.MyMessage.newBuilder()
                    .setDataType(OuterMessage.MyMessage.DataType.DogType)
                    .setDog(
                            OuterMessage.Dog.newBuilder()
                                    .setName("doggggg")
                                    .setAge(3)
                                    .build()
                    )
                    .build();
        } else {
            myMessage = OuterMessage.MyMessage.newBuilder()
                    .setDataType(OuterMessage.MyMessage.DataType.CatType)
                    .setCat(
                            OuterMessage.Cat.newBuilder()
                                    .setName("cattttt")
                                    .setAge(3)
                                    .build()
                    )
                    .build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
