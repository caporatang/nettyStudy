package com.example.nettystudy.netty.chapter4.multiple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * packageName : com.example.nettystudy.netty.chpater4.multiple
 * fileName : EchoServerHandlerV4FirstHandler
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class EchoServerHandlerV4FirstHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readMessage = (ByteBuf) msg;
        System.out.println("FirstHandler channelRead : " + readMessage.toString(Charset.defaultCharset()));
        ctx.write(msg);

        // 두 번째 핸들러의 channelRead 이벤트를 수행하고 싶으면 코드를 수정해야한다.
        // 다음 이벤트 핸들러로 이벤트를 넘겨주는 방법은 ChannelHandlerContext 인터페이스를 사용하여 채널 파이프라인에 이벤트를 발생시키는 것이다.
        // ChannelHandlerContext 인터페이스의 fireChannelRead 메서드를 호출하면 네티는 채널 파이프라인에 channelRead 이벤트를 발생시킨다.
        // 이벤트 메서드의 이름에 fire라는 접두어가 붙은 메서드가 이벤트를 발생시키는 메서드다.
        ctx.fireChannelRead(msg);
    }
}