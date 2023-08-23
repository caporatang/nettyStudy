package com.example.nettystudy.netty.chapter1.discardServer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
    // 데이터가 수신되었을 때 자동으로 수행되는 Discard 서버의 데이터 처리 핸들러.

    // 두가지 메서드는 네티가 제공하는 '이벤트' 이다.
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // DiscardServer 에서 지정한 8888번 포트로 접속한 클라이언트가 데이터를 전송하면 이 메서드가 자동으로 실행된다
        // 당장은 아무것도 하지 않는다.
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}