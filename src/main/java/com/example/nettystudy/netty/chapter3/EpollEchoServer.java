package com.example.nettystudy.netty.chapter3;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter3
 * fileName : EpollEchoServer
 * author : taeil
 * date : 2023/08/27
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/27        taeil                   최초생성
 */
public class EpollEchoServer {
    // 주의점 : Epoll 입출력 모드는 리눅스 운영체제에서만 동작한다 !!


    public static void main(String[] args) {
        // EventLoopGroup 인터페이스를 상송받은 EpollEventLoopGroup 클래스의 객체가 스레드 그룹에 설정되고,
        // 서버 채널에 epoll 입출력 모드를 위한 EpollServerSocketChannel 클래스 설정
        EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
        EventLoopGroup workerGroup = new EpollEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(EpollServerSocketChannel.class) // 서버 소켓 채널의 클래스를 OioServerSocketCHannel로 지정
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