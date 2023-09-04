package com.example.nettystudy.netty.chpater4.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.concurrent.EventExecutorGroup;
import org.apache.tomcat.util.buf.Ascii;

import java.net.http.HttpRequest;

import static io.netty.handler.codec.rtsp.RtspResponseStatuses.OK;

/**
 * packageName : com.example.nettystudy.netty.chpater4.codec
 * fileName : HttpHelloWorldServerHandler
 * author : taeil
 * date : 2023/09/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/04        taeil                   최초생성
 */
public class HttpHelloWorldServerHandler extends ChannelInboundHandlerAdapter {
    // HttpServerCodec으로부터 수신된 channelRead 이벤트를 처리해야 하므로 ChannelInbound HandlerAdapter 추상 클래스를 상속받는다.


    // 채널 파이프라인에 등록된 HttpServerCodec으로부터 수신된 HTTP 객체를 처리하는 channelRead 이벤트의 구현체다
    // channelRead 이벤트로 수신되는 HTTP 객체는 HttpRequest, HttpMessage, LastHttpContent로 구분된다.


    // 웹 브라우저로 전송할 메세지를 상수로 선언
    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};

    private static final AsciiString CONTENT_TYPE = new AsciiString("Content-Type");
    private static final AsciiString CONTENT_LENGTH = new AsciiString("Content-Length");
    private static final AsciiString CONNECTION = new AsciiString("Connection");
    private static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 웹 브라우저로부터 데이터가 모두 수신되었을 때 채널 버퍼의 내용을 웹 브라우저로 전송
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // HttpServerCodec으로부터 수신된 channelRead 이벤트를 처리하려면 ChannelRead를 오버라이드 한다.

        if(msg instanceof HttpRequest) {
            // 수신된 객체가 HttpRequest 일 때 HttpResponse 객체를 생성하고 헤더와 HelloWorld 메세지를 저장한다.
            HttpRequest req = (HttpRequest) msg;

            if(HttpHeaders.is100ContinueExpected((HttpMessage)req)) {
                ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
            }

            boolean keepAlive = HttpHeaders.isKeepAlive((HttpMessage) req);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            if(!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



    // 예제에서는 채널 파이프라인에 HttpServerCodec과 HttpHelloWorldServerHandler 클래스를 순서대로 설정했다.
    // HttpServerCodec 클래스는 인바운드와 아웃바운드 이벤트 핸들러를 모두 구현한다.
    // 이벤트는 인바운드와 아웃바운드로 나뉘고 채널에서 발생한 이벤트에 따라서 순서대로 이벤트 핸들러를 거쳐간다.
    // 채널 파이프라인에 등록된 이벤트 핸들러의 순서가 중요하다

}