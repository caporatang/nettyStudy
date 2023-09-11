package com.example.nettystudy.netty.chapter4.multiple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chpater4.multiple
 * fileName : EchoServerHandlerV3FirstHandler
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class EchoServerHandlerV3FirstHandler extends ChannelInboundHandlerAdapter {
    // 첫 번째 이벤트 핸들러

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readMessage = (ByteBuf) msg;
        System.out.println("ChannelRead : " + readMessage.toString(Charset.defaultCharset()));
        ctx.write(msg);
    }
}