package com.example.netty_chat_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Channel;
import java.util.List;

/**
 * packageName : com.example.netty_chat_server
 * fileName : ChatServerHandler
 * author : taeil
 * date : 2023/09/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/21        taeil                   최초생성
 */
@Slf4j
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    // 기본적인 채팅 서버에 대한 '핸들러' 를 만들어보자 ~


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        log.info("Receive message : {}", message);

        ctx.channel().parent().writeAndFlush("[" + ctx.channel().remoteAddress() + "] : " + message + "\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public ChatServerHandler(List<Channel> channels) {

    }
}