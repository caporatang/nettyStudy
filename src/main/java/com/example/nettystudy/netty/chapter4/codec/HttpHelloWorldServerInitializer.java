package com.example.nettystudy.netty.chapter4.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * packageName : com.example.nettystudy.netty.chpater4.codec
 * fileName : HttpHelloWorldServerInitializer
 * author : taeil
 * date : 2023/09/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/04        taeil                   최초생성
 */
public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpHelloWorldServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if(sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // 네티가 기본적으로 제공하는 HTTP 서버 코덱 : 인바운드와 아웃바운드 핸들러를 모두 구현한다.
        // 즉, HttpServerCodec의 생성자에서 HttpRequestDecoder와 HttpResponseEncoder를 모두 생성한다.
        // HttpServerCodec은 간단한 웹 서버를 생성하는 데 사용되는 코덱으로서 수신된 ByteBuf 객체를 HttpRequest와 HttpContent 객체로 변환하고 HttpResponse 객체를 ByteBuf로 인코딩하여 송신한다
        p.addLast(new HttpServerCodec());

        // HttpServerCodec이 수신한 이벤트와 데이터를 처리하여 HTTP 객체로 변환한 다음 channelRead 이벤틀르 HttpHelloWorldServerHandler 클래스로 전달한다.
        p.addLast(new HttpHelloWorldServerHandler());



        // ---------------- 순 서 중 요 -----------------
        // 채널 파이프라인에 코덱과 핸들러의 순서를 반대로 설정하고 서버를 실행하면, 웹 서버는 정상적으로 실행되는데 이때 웹 브라우저를 사용하여 접속하면 아무런 응답도 받지 못한다.
        // 채널로 데이터가 수신되면 가장 먼저 HttpHelloWorldServerHandler 이벤트 핸들러의 channelRead 이벤트 메서드가 수행되기 때문이다.
        // 즉, HttpHelloWorldServerHandler의 channelRead 이벤트 메서드에서는 자신이 수신한 이벤트를 처리하고 나서 다음 이벤트 핸들러로 이벤트를 전달하지 않았기 떄문에 HttpServerCodec 이벤트 핸들러가 실행되지 않는다.
    }
}