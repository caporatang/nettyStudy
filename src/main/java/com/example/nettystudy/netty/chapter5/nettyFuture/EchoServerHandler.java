package com.example.nettystudy.netty.chapter5.nettyFuture;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * packageName : com.example.nettystudy.netty.chapter5.nettyFuture
 * fileName : EchoServerHandler
 * author : taeil
 * date : 2023/09/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/12        taeil                   최초생성
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    // 지금까지 작성했던 에코 서버의 이벤트 핸들러와 다른 점은 데이터 전송 이후의 연결 해제 부분.
    // 연결된 클라이언트로부터 데이터를 수신하면 데이터를 돌려주고 데이터의 전송이 완료되면 연결된 소켓 채널을 닫음

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 수신된 데이터를 클라이언트 소켓 버퍼에 기록하고 버퍼의 데이터를 채널로 전송하는 비동기 메서드인 writeAndFlush를 호출하고 ChannelFUture 객체를 돌려받음
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);

        // ChannelFuture 객체에 채널을 종료하는 리스너를 등록한다.
        // ChannelFutureListener.CLOSE 리스너는 네티가 제공하는 기본 리스너로서 ChannelFuture 객체가 완료 이벤트를 수신할 때 수행된다.
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // 네티는 기본 채널 리스너를 제공한다.
    // ChannelFutureListener 인터페이스를 통해서 사용 가능하다.
    // ChannelFutureListener.CLOSE : ChannelFuture 객체자 작업 완료 이벤트를 수신했을 때 ChannelFuture 객체에 포함된 채널을 닫는다. -> 작업 성공 여부와 상관없이 수행
    // ChannelFutureListener.CLOSE_ON_FAILURE : ChannelFuture 객체가 완료 이벤트를 수신하고 결과가 실패일 때 ChannelFUture 객체에 포함된 채널을 닫는다.
    // ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE : ChannelFuture 객체가 완료 이벤트를 수신하고 결과가 실패일 때 채널 예외 이벤트를 발생시킨다.
}