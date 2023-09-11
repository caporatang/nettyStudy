package com.example.nettystudy.netty.chapter4.multiple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * packageName : com.example.nettystudy.netty.chpater4.multiple
 * fileName : EchoServerV3SecondHandler
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class EchoServerV3SecondHandler extends ChannelInboundHandlerAdapter {
    // 두 번째 이벤트 핸들러

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete 발생");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}