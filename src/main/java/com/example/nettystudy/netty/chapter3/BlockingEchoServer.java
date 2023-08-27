package com.example.nettystudy.netty.chapter3;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter3
 * fileName : BlockingEchoServer
 * author : taeil
 * date : 2023/08/27
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/27        taeil                   최초생성
 */
public class BlockingEchoServer {
    // blocking 입출력 모드를 지원하는 ServerBootstrap

    // 단순히 코드 세줄을 변경해서 논블로킹 모드 소켓을 지원하는 서버를 블로킹 모드 소켓을 지원하는 서버 애플리케이션으로 변경되었다.
    // 부트스트랩이 우아한 추상화 모델을 제공하기 떄문에 네트워크 애플리케이션 개발자가 확장성과 유연성을 동시에 만족하는 코드를 작성할 수 있다.

    public static void main(String[] args) {
        // 소켓 모드를 블로킹 모드로 변경하려면 OioEventLoopGroup로 변경
        EventLoopGroup bossGroup = new OioEventLoopGroup(1); // Oio : Old-Blocking-IO
        EventLoopGroup workerGroup = new OioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(OioServerSocketChannel.class) // 서버 소켓 채널의 클래스를 OioServerSocketCHannel로 지정
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}