package com.example.nettystudy.netty.chapter4.eventHnadler.channelInboundEvent.channelRead;

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
public class EchoServerV1Handler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 이벤트가 실행될 때의 인수 msg 객체에 수신된 데이터가 담겨있다 -> ByteBuf 인터페이스로 변환
        // 네티 내부에서는 모든 데이터가 ByteBuf로 관리된다.
        ByteBuf readMessage = (ByteBuf) msg;
        System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));

        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}