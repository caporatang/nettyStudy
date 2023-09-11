package com.example.nettystudy.netty.chapter4.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * packageName : com.example.nettystudy.netty.chpater4.codec
 * fileName : HttpHelloWorldServer
 * author : taeil
 * date : 2023/09/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/04        taeil                   최초생성
 */
public class HttpHelloWorldServer {
    // 사용자 정의 코덱
    // 네티 기본 제공 코덱은 인바운드와 아웃바운드 핸들러를 구현했다 -> 네트워크 입출력 프로토콜을 구현한 것이다.
    // 사용자 정의 코덱은 사용자가 직접 필요한 프로토콜을 구현하는 것이다.
    // 네티에서 기본 제공하는 HTTP 코덱을 사용하는 방법을 알아보자!


    // 서버의 SSL 지원 여부를 설정한다.
    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));

    public static void main(String[] args) throws CertificateException, SSLException {
        // Configure SSL.
        final SslContext sslCtx;

        if(SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
            ssc.privateKey();
        } else {
            sslCtx = null;
        }

        //Configure the server
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 채널 파이프라인 설정
                    .childHandler(new HttpHelloWorldServerInitializer(sslCtx));

            Channel ch = b.bind(PORT).sync().channel();

            System.out.println("Open your web browser and navigate to " + (SSL ? "https" : "http") + "://127.0.0.1:" + PORT + '/');
            ch.closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}