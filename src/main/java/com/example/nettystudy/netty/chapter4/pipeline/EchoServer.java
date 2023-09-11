package com.example.nettystudy.netty.chapter4.pipeline;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chpater4
 * fileName : EchoServer
 * author : taeil
 * date : 2023/08/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/31        taeil                   최초생성
 */
public class EchoServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // childHandler 메서드를 통해서 연결된 클라이언트 소켓 채널이 사용할 채널 파이프라인을 작성.
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ChannelInitializer 클래스의 initChannel 메서드 본체는 부트스트랩이 초기화될 때 수행되며 이때 서버 소켓 채널 파이프라인이 연결된다.
                            // initChannel 메서드는 클라이언트 소켓 채널이 생성될 때 자동으로 호출되는데 이때 채널 파이프라인의 설정을 수행한다.


                            // initChannel 메서드의 인자로 입력된 소켓 채널(즉, 연결된 클라이언트 소켓 채널)에 설정된 채널 파이프라인을 가져오게 되는데,
                            // 네티의 내부에서는 클라이언트 소켓 채널을 생성할 때 빈 채널 파이프라인 객체를 생성하여 할당한다.
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandler()); // 이벤트 핸들러를 addLast 메서드를 사용하여 등록한다.
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}