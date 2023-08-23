package com.example.nettystudy.netty.chapter1.discardServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter1.discardServer
 * fileName : DiscardServer
 * author : taeil
 * date : 2023/08/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/23        taeil                   최초생성
 */
public class DiscardServer {
    // main 메서드를 포함하는 통상적인 자바 애플리케이션 코드 구조에 추가적으로 네티의 이벤트루프, 부트스트랩, 채널 파이프라인, 핸들러 등의 클래스를 초기화하는 코드를 가지고 있다.


    public static void main(String[] args) {
        // 서버의 8888번 포트에 클라이언트 접속을 허용하고 클라이언트로부터 받은 데이터를 DiscardServerHandler 클래스가 처리하도록 지정.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new DiscardServerHandler()); // 접속된 클라이언트로부터 수신된 데이터를 처리할 핸들러를 지정한다.
                        }
                    });

            ChannelFuture f = b.bind(8888).sync(); // 부트스트랩 클래스의 bind 메서드로 접속할 포트를 지정한다.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}