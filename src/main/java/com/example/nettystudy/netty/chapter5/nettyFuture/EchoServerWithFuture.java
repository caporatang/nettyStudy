package com.example.nettystudy.netty.chapter5.nettyFuture;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter5.nettyFuture
 * fileName : EchoServerWithFuture
 * author : taeil
 * date : 2023/09/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/12        taeil                   최초생성
 */
public class EchoServerWithFuture {
    // 기존에 작성하던 EchoServer를 Future 객체 반환을 명시해보자.

    // 네티의 ChannelFuture는 채널 I/O의 비동기 호출을 지원하고자 제공된다.
    // 비동기 I/O 메서드 호출의 결과로 ChannelFuture 객체를 돌려받게 되고 이 객체를 통해서 작업의 완료 유무를 확인할 수 있다.

    // 추가적으로 애플리케이션에서 작업의 완료 유무를 확인하려고 while 루프를 작성하여 작업의 완료 유무를 확인하는 방식은 코드의 복잡성이 증가해 좋은 코드패턴이 아님.
    // 네티는 애플리케이션 작업 완료되었을 때 수행할 채널 리스너를 설정할 수 있다.

    public static void main(String[] args) {
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
                            p.addLast(new EchoServerHandler());
                        }
                    });

            // Future 객체로 반환
            // 에코 서버가 8888번 포트를 사용하도록 바인드하는 비동기 bind 메서드를 호출한다.
            // 부트스트랩 클래스의 bind 메서드는 포트 바인딩이 완료되기 전에 ChannelFuture 객체를 돌려준다.
            ChannelFuture bindFuture = b.bind(8888).sync();

            // ChannelFuture 인터페이스의 sync 메서드는 주어진 ChannelFuture 객체의 작업이 완료될 때까지 블로킹하는 메서드.
            // bind 메서드의 처리가 완료될 때 sync 메서드도 같이 완료된다.
            bindFuture.sync();

            // bindFuture 객체를 통해서 채널을 얻어옴. -> 얻어진 채널은 8888번 포트에 바인딩된 서버 채널이다.
            Channel serverChannel = bindFuture.channel();

            // 바인드가 완료된 서버 채널의 CloseFuture 객체를 돌려준다.
            // 네티내부에서는 채널이 생성될 때 CloseFuture 객체도 같이 생성되므로 closeFuture 메서드가 돌려주는 CloseFuture 객체는 항상 동일한 객체다.
            ChannelFuture closeFuture = serverChannel.closeFuture();

            // CloseFuture 객체는 채널의 연결이 종료될 때 연결 종료 이벤트를 받는다.
            // 채널이 생성될 때 같이 생성되는 기본 CloseFuture 객체에는 아무 동작도 설정되어 있지 않으므로 이벤트를 받았을 때 아무 동작도 하지 않는다.
            closeFuture.sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

}