package com.example.nettystudy.netty.chapter1.echoServer.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter1.echoServer.client
 * fileName : EchoClient
 * author : taeil
 * date : 2023/08/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/23        taeil                   최초생성
 */
public class EchoClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)                                         // 클라이언트 측은 서버와 달리 서버에 연결된 채널 하나만 존재하기 때문에 이벤트 루프 그룹이 하나다.
                .channel(NioSocketChannel.class)                   // 클라이언트 애플리케이션이 생성하는 채널의 종류를 설정한다.
                .handler(new ChannelInitializer<SocketChannel>() { // 클라이언트 애플리케이션이므로 채널 파이프라인의 설정에 일반 소켓 채널 클래스인 SocketChannel을 설정.
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new EchoClientHandler());
                    }
                });

            // 비동기 입출력 메서드인 connect를 호출한다.
            // connect 메서드는 ChannelFuture 객체를 돌려준다. 이 객체를 통해서 비동기 메서드의 처리 결과를 확인할 수 있다.
            // ChannelFuture 객체의 sync 메서드는 ChannelFuture 객체의 요청이 완료될 때까지 대기한다. -> 단, 요청이 실패하면 예외를 던진다. 즉, connect메서드의 처리가 완료될 때까지 다음 라인으로 진행되지 않는다.
            ChannelFuture f = b.connect("localhost", 8888).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}