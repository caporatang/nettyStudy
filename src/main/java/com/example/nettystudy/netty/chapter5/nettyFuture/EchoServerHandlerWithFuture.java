package com.example.nettystudy.netty.chapter5.nettyFuture;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * packageName : com.example.nettystudy.netty.chapter5.nettyFuture
 * fileName : EchoServerHandlerWithFuture
 * author : taeil
 * date : 2023/09/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/12        taeil                   최초생성
 */
public class EchoServerHandlerWithFuture extends ChannelInboundHandlerAdapter {
    // 사용자 정의 리스너
    // EchoServerHandlerWithFuture 클래스는 channelRead 이벤트에서 수신한 데이터를 클라이언트로 돌려주고 전송한 데이터 크기를 출력한 다음 클라이언트 채널을 닫는다.


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);


        // 네티가 수신한 msg 객체는 ByteBuf 객체다.
        // 클라이언트로부터 수신한 데이터를 클라이언트로 되돌려주므로 전송한 데이터의 크기는 msg 객체의 크기와 같다. msg 객체에 저장된 데이터의 크기를 final 변수에 저장
        final int writeMessageSize = ((ByteBuf)msg).readableBytes();


        // 사용자 정의 채널 리스너를 생성하여 ChannelFuture 객체에 할당.
        channelFuture.addListener(new ChannelFutureListener() {

            @Override
            // operationComplete 메서드는 ChannelFUture 객체에서 발생하는 작업 완료 이벤트 메서드로서 사용자 정의 채널 리스너 구현에 포함되어야 한다.
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("전송한 Bytes : " + writeMessageSize);
                future.channel().close(); // ChannelFUture 객체에 포함된 채널을 가져와서 채널 닫기 이벤트 발생
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}