package cn.coderjia.netty.sr;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @Author CoderJiA
 * @Description TestPooled
 * @Date 10/6/2019 2:31 PM
 **/
public class TestPooled {

    public static void main(String[] args) {

        PooledByteBufAllocator pooledByteBufAllocator = new PooledByteBufAllocator();
        ByteBuf buffer = pooledByteBufAllocator.buffer(5);


    }

}
