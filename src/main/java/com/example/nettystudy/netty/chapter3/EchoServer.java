package com.example.nettystudy.netty.chapter3;

import com.example.nettystudy.netty.chapter1.echoServer.server.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * packageName : com.example.nettystudy.netty.chapter3
 * fileName : EchoServer
 * author : taeil
 * date : 2023/08/27
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/27        taeil                   최초생성
 */
public class EchoServer {
    // 보통 ServerBootstrap은 애플리케이션이 시작할 때 초기화된다.

    public static void main(String[] args) {
        // 논블로킹 소켓 모드의 서버 애플리케이션을 설정하는 예제

        // 부모 스레드 : 각 이벤트 루프 그룹은 NIO이벤트 그룹이고, 스레드 그룹 내에서 생성할 최대 스레드 수를 의미 -> 부모 스레드 그룹은 단일 스레드로 동작한다.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // 자식 스레드 : NioEventLoopGroup의 인수없는 생성자는 사용할 스레드 수를 서버 애플리케이션이 동작하는 하드웨어 코어 수를 기준으로 결정한다.
        // 스레드 수는 하드웨어가 가지고 있는 CPU 코어 수의 2배를 사용한다. -> 서버 애플리케이션이 동작하는 하드웨어가 4코어 CPU, 하이퍼 스레딩을 지원한다면 16개 생성
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 부트스트랩은 빌더 패턴으로 작성되어 있다. -> 인수없이 객체 생성
            ServerBootstrap b = new ServerBootstrap();

            // channel, childHandler, initChannel 메서드로 호출한다.
            // ServerBootstrap 객체에 서버 애플리케이션이 사용할 두 스레드 그룹을 설정
            // 첫 번째 스레드 그룹은 클라이언트의 연결을 수락하는 부모 스레드 그룹, 두번쨰 스레드 그룹은 연결된 클라이언트의 소켓으로부터 데이터 입출력 및 이벤트 처리를 담당하는 자식 스레드 그룹이다.
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // 서버 소켓(부모 스레드)이 사용할 네트워크 입출력 모드 설정
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 자식 채널의 초기화 방법 설정 및 연결된 채널이 초기화될 때의 기본 동작이 지정된 추상 클래스
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline(); // 채널 파이프라인 객체 생성
                            p.addLast(new EchoServerHandler()); // 채널 파이프라인에 Handler 등록, Handler는 클라이언트의 연결이 생성되었을 때 데이터 처리를 담당
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }


}