package com.example.nettystudy.netty.chapter1.echoServer.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chapter1.echoServer.client
 * fileName : EchoClientHandler
 * author : taeil
 * date : 2023/08/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/23        taeil                   최초생성
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // channelActive 이벤트는 ChannelInboundHandler에 정의된 소켓 채널이 최초 활성화 되었을 때 실행된다.
        String sendMessage = "Hello, Netty!";

        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(sendMessage.getBytes());

        StringBuilder builder = new StringBuilder();
        builder.append("전송할 문자열 [");
        builder.append(sendMessage);
        builder.append("]");

        System.out.println(builder.toString());

        // writeAndFlus 메서드는 내부적으로 데이터 기록과 전송의 두 가지 메서드를 호출
        // 첫 번째는 채널에 데이터를 기록하는 write(), 두 번째는 채널에 기록된 데이터를 서버로 전송하는 flush()
        ctx.writeAndFlush(messageBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 서버로부터 수신된 데이터가 있을 때 호출되는 네티 이벤트 메서드

        // 서버로부터 수신된 데이터가 저장된 msg 객체에서 문자열 데이터를 추출.
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());

        StringBuilder builder = new StringBuilder();
        builder.append("전송할 문자열 [");
        builder.append(readMessage);
        builder.append("]");

        System.out.println(builder.toString());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 수신된 데이터를 모두 읽었을 때 호출되는 이벤트 메서드. -> channelRead 메서드의 수행이 완료되고나서 자동으로 호출된다.

        // 수신된 데이터를 모두 읽은 후 서버와 연결된 채널을 닫는다. 이후 데이터 송수신 채널은 닫히게 되고 클라이언트 프로그램은 종료.
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}