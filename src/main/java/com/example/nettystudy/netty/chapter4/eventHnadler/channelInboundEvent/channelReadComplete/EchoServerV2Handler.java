package com.example.nettystudy.netty.chapter4.eventHnadler.channelInboundEvent.channelReadComplete;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent
 * fileName : EchoServerV2Handler
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class EchoServerV2Handler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readMessage = (ByteBuf) msg;
        System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete 발생");


        // channelRead 에서 사용한 handler 와는 다른 동작을 한다.
        // 그 이유는 flush 메서드 때문이다.
        // flush 메서드는 네티의 채널 버퍼에 저장된 데이터를 상대방으로 즉시 전송한다.
        // 이로 인해서 EchoServerV1은 데이터를 수신하자마자 상대방에게 데이터를 전송하고
        // EchoServerV2는 소켓 채널에서 더 이상 읽어들일 데이터가 없을 때 channelReadComplete 이벤트에서 데이터를 전송한다


        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}