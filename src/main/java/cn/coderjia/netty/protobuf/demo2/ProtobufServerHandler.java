package cn.coderjia.netty.protobuf.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author CoderJiA
 * @Description ProtobufServerHandler
 * @Date 6/2/19 下午1:48
 **/
public class ProtobufServerHandler extends SimpleChannelInboundHandler<OuterMessage.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OuterMessage.MyMessage myMessage) throws Exception {
        OuterMessage.MyMessage.DataType dataType = myMessage.getDataType();
        if (dataType == OuterMessage.MyMessage.DataType.PersonType) {
            OuterMessage.Person person = myMessage.getPerson();
            System.out.println("person: {" + "name:" + person.getName() + "\t" + "age:" + person.getAge() + "\t" + "address:" + person.getAddress() + "}");
        } else if (dataType == OuterMessage.MyMessage.DataType.DogType) {
            OuterMessage.Dog dog = myMessage.getDog();
            System.out.println("dog: {" + "name:" + dog.getName() + "\t" + "age:" + dog.getAge() + "}");
        } else {
            OuterMessage.Cat cat = myMessage.getCat();
            System.out.println("cat: {" + "name:" + cat.getName() + "\t" + "age:" + cat.getAge() + "}");
        }
     }


}