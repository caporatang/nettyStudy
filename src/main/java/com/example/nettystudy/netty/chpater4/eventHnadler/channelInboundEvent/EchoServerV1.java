package com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent
 * fileName : EchoServerV1
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class EchoServerV1 {
    public static void main(String[] args) {
        // 에코 서버
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // childHandler 메서드를 통해서 연결된 클라이언트 소켓 채널이 사용할 채널 파이프라인을 작성.
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerV1Handler());
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