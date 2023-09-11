package com.example.nettystudy.netty.chapter4.multiple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chpater4
 * fileName : EchoServerV2
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class EchoServerV3 {
    // 여러 이벤트 핸들러가 등록되어 있을 때 어떻게 이벤트 메서드가 실행되는지에 대한 예제

    // 한 이벤트 핸들러에 구현되어 있던 코드를 ㄷ두 이벤트 핸들러로 분리하여 채널 파이프라인에 등록했을 뿐이다.
    // EchoServerV3를 실행하면 다른 예제인 EchoServerV2 와 동일하게 동작한다.

    // 순서에 따라 channelActive 이벤트 부터 시작해야 하지만 handler에 구현되어 있지 않기 때문에 무시된다.
    // 여러 개의 이벤트 핸들러가 등록되어 있을 때 이벤트에 해당하는 이벤트 메서드가 실행된다

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
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new EchoServerHandlerV3FirstHandler());
                            p.addLast(new EchoServerV3SecondHandler());
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