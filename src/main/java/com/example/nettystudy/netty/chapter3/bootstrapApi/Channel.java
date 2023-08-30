package com.example.nettystudy.netty.chapter3.bootstrapApi;

/**
 * packageName : com.example.nettystudy.netty.chapter3.bootstrapApi
 * fileName : Channel
 * author : taeil
 * date : 2023/08/30
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/30        taeil                   최초생성
 */
public class Channel {
    // 소켓의 입출력 모드를 설정하는 channel 메서드

    // chennel 메서드는 AbstractBootstrap 추상 클래스의 구현체인 ServerBootstrap과 Bootstrap 클래스에 모두 존재하는 API이며, 부트스트랩 클래스를 통해서 생성된 채널의 입출력 모드를 설정할 수 있다.
    // 부트스트랩의 channel 메서드에 등록된 소켓 채널 생성 클래스가 소켓 채널을 생성한다.
    // chennel 메서드는 AbstractBootstrap 클래스에 정의 되어 있다.

    // --------------- 설정 가능한 클래스 목록 ---------------
    //  LocalServerChannel.class : 하나의 자바 가상머신에서 가상 통신을 위한 서버 소켓채널을 생성하는 클래스
    // OioServerSocketChannel.class : 블로킹 모드의 서버 소켓 채널을 생성하는 클래스
    // NioServerSocketChannel.class : 논블로킹 모드의 서버 소켓 채널을 생성하는 클래스
    // EpollServerSocketChannel.class : 리눅스 커널의 epoll 입출력 모드를 지원하는 서버 소켓채널을 생성하는 클래스
    // OioSctpServerChannel.class : SCTP 전송계층을 사용하는 블로킹 모드의 서버 소켓채널을 생성하는 클래스
    // NioSctpServerChannel.class : SCTP 전송계층을 사용하는 논블로킹모드의 서버 소켓채널을 생성하는 클래스
    // NioUdtByteAcceptorChannel.class : UDT 프로토콜을 지원하는 논블로킹 모드의 서버 소켓 채널을 생성하는 클래스 -> 내부적으로 스트림 데이터를 처리하도록 구현되어 있으며 barchart-udt 라이브러리를 사용한다.
    // NioUdtMessageAcceptorChannel.class : UDT 프로토콜을 지원하는 블로킹 모드의 서버 소켓 채널을 생성하는 클래스. 내부적으로 데이터그램 패킷을 처리하도록 구현되어 있다.

    // 이 클래스들의 설명에 서버 소켓 채널을 생성하는 클래스들은 모두 io.netty.channel 패키지의 ServerChannel 인터페이스를 구현하고 있다.
    // ServerBootStrap의 channel 메서드에는 ServerChannel 인터페이스를 상속받은 클래스를 인수로 사용할 수 있다.

}