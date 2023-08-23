package com.example.nettystudy.netty.chapter1.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chapter1.discardServer
 * fileName : DiscardServerHandler
 * author : taeil
 * date : 2023/08/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/23        taeil                   최초생성
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter { // 입력된 데이터를 처리하는 이벤트 핸들러인 ChannelInboundHandlerAdapter를 상속받게 지정한다.
    // discardServerHandler와 몇 가지 차이점이 있다.
    // 1. 상속받은 클래스
    // 1-1) DiscardServerHandler : SimpleChannelInboundHandler
    // 1-2) EchoServerHandler : ChannelInboundHandlerAdapter

    // 두 클래스 모두 네티에서 기본으로 제공하는 클래스로서 클라이언트로부터 수신한 데이터를 처리하는 이벤트를 제공
    // SimpleChannelInboundHandler 클래스는 ChannelInboundHandlerAdapter 를 상속받았다.
    // 즉, SimpleChannelInboundHandler 클래스에서는 데이터가 수신 되었을 때 호출되는 channelRead 이벤트에 대한 처리가 이미 구현되어 있다.

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception { // 데이터 수신 이벤트 처리 메서드다. 클라이언트로부터 데이터의 수신이 이루어졌을 때 네티가 자동으로 호출하는

        // 수신된 데이터를 가지고 있는 네티의 바이트 버퍼 객체로부터 문자열 데이터를 읽어온다 -> 자바의 바이트 버퍼 java.nio.ByteBuffer와 유사한 클래스. 더 나은 성능과 편의성을 제공하는 네티의 버퍼 클래스이다.
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());

        System.out.println("수신한 문자열 [" + readMessage + ']');

        // ctx는 ChannelHandlerContext 인터페이스의 객체로서 채널 파이프라인에 대한 이벤트를 처리한다.
        // 여기서는 서버에 연결된 클라이언트 채널로 입력받은 데이터를 그대로 전송한다.
        ctx.write(msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // channelRead 이벤트의 처리가 완료된 후 자동으로 수행되는 이벤트 메서드로서 채널 파이프라인에 저장된 버퍼를 전송하는 flush 메서드를 호출한다.
        ctx.flush();
    }
}