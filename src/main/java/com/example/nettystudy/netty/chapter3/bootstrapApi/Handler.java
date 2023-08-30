package com.example.nettystudy.netty.chapter3.bootstrapApi;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * packageName : com.example.nettystudy.netty.chapter3.bootstrapApi
 * fileName : Handler
 * author : taeil
 * date : 2023/08/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/30        taeil                   최초생성
 */
public class Handler {
    // 서버 소켓 채널의 이벤트를 처리할 핸들러 설정 API

    // 이 메서드를 통해서 등록되는 이벤트 핸들러는 서버 소켓 채널에서 발생하는 이벤트를 수신하여 처리한다.

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b= new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler());
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    // ServerBootstrap의 handler 메서드로 LoggingHandler를 설정하는 방법이다.
    // LoggingHandler는 네티에서 기본으로 제공하는 코덱이다. -> 채널에서 발생하는 모든 이벤트를 로그로 출력해준다.

    // -------- 서버 실행 로그 --------
    // [nioEventLoopGroup-2-1] DEBUG c.g.nettybook.ch0.LoggingHandler - [id : 0x57717c47] REGISTERED
    // [nioEventLoopGroup-2-1] DEBUG c.g.nettybook.ch0.LoggingHandler - [id : 0x57717c47] BIND: 0.0.0.0/0.0.0.0:8888
    // [nioEventLoopGroup-2-1] DEBUG c.g.nettybook.ch0.LoggingHandler - [id : 0x57717c47, 0.0.0.0/0.0.0.0:8888] ACTIVE

    // 서버를 실행하면 부트스트랩 설정에 따라서 서버 소켓 채널 클래스의 초기화 로그가 출력된다.
    // -> 채널이 이벤ㅌ트 루프에 등록되고 8888번 포트에 바인드되고 8888번 포트가 활성화되었다는 로그가 출력된다.

    //  -------- telnet으로 클라이언트 접속 시.. --------
    // // [nioEventLoopGroup-2-1] DEBUG c.g.nettybook.ch0.LoggingHandler -
    // [id : 0x57717c47, 0.0.0.0/0.0.0.0:8888] RECEIVED: [id: 0xe8e68342, / 192.168.56.102:37365 => /192.168.56.1:8888]

    // LoggingHandler는 네티가 제공하는 ChannelDuplexHandler를 상속받는다.
    // ChannelInboundHandler 와 ChannelOutboundHandler 인터페이스를 상속받아 구현한다. -> 채널에서 발생하는 양방향 이벤트 모두를 로그로 출력하도록 구현되어 있다.

    // 연결된 클라이언트와 서버 간의 데이터 송수신 이벤트에 대한 로그는 출력해주지 않는다.
    // ServerBootstrap의 handler 메서드로 등록된 이벤트 핸들러는 서버 소켓 채널에서 발생한 이벤트만을 처리하기 때문이다.
    // 서버 소켓 채널에서는 활성화 이벤트만 발생된것이다.

}