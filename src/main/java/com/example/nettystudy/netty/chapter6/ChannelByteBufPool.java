package com.example.nettystudy.netty.chapter6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chapter6
 * fileName : ChannelByteBufPool
 * author : taeil
 * date : 2023/09/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/20        taeil                   최초생성
 */
public class ChannelByteBufPool extends ChannelInboundHandlerAdapter {
    // 네티 내부에서 데이터를 처리할 때 자바의 바이트 버퍼가 아닌 네티 바이트 버퍼를 사용한다.
    // channelRead 메서드가 실행된 이후의 네티 바이트 버퍼는 바이트 버퍼 풀로 돌아간다.
    // 네티 바이트 버퍼 풀은 네티 애플리케이션의 서버 소켓 채널이 초기화될 때 같이 초기화되며 ChannelHandlerContext 인터페이스의 alloc 메서드로 생성된 바이트 버퍼 풀을 참조할 수 있다.

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readMessage = (ByteBuf) msg;

        System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));


        // ChannelHandlerContext를 통해서 네티 프레임워크에서 초기화된 ByteBufAllocator를 참조할 수 있다.
        // ByteBufAllocator는 바이트 버퍼 풀을 관리하는 인터페이스며 플랫폼의 지원 여부에 따라 다이렉트 버퍼와 힙 버퍼 풀을 생성한다.
        // 기본적으로 다이렉트 버퍼 풀을 생성하며, 개발자의 필요에 따라 힙 버퍼 풀을 생성할 수도 있다.
        ByteBufAllocator byteBufAllocator = ctx.alloc();

        // ByteBufAllocator의 buffer 메서드를 사용하여 생성된 바이트 버퍼는
        // ByteBufAllocator의 풀에서 관리되며 바이트 버퍼를 채널에 기록하거나 명시적으로 release 메서드를 호출하면 바이트 버퍼 풀로 돌아간다.
        ByteBuf newBuffer = byteBufAllocator.buffer();

        // new Buffer

        // write 메서드의 인수로 바이트 버퍼가 입력되면 데이터를 채널에 기록하고 난 뒤에 버퍼 풀로 돌아간다.
        ctx.write(msg);
    }
}