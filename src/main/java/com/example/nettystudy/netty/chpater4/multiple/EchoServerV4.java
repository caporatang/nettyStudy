package com.example.nettystudy.netty.chpater4.multiple;

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
public class EchoServerV4 {
    // 채널 파이프라인에 동일한 이벤트 메서드를 구현한 이벤트 핸들러가 여럿일 때 어떻게 동작하는지에 대한 예제

    // 실행 결과는 첫 번쨰 이벤트 핸들러만 실행되고 두 번쨰 이벤트 핸들러는 실행되지 않는다.
    // 이벤트에 해당하는 이벤트 메서드가 수행되면서 이벤트가 사라졌기 때문이다.
    // 즉, 하나의 이벤트는 하나의 이벤트 메서드만 수행된다.

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
                            p.addLast(new EchoServerHandlerV4FirstHandler());
                            p.addLast(new EchoServerV4SecondHandler());
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