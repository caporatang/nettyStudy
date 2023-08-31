package com.example.nettystudy.netty.chpater4;

/**
 * packageName : com.example.nettystudy.netty.chpater4
 * fileName : InitializerPipeLine
 * author : taeil
 * date : 2023/08/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/31        taeil                   최초생성
 */
public class InitializerPipeLine {
    // 채널 파이프라인이 초기화되는 순서 : InitializerPipeLine.png 참조!

    // 1. 클라이언트 연결에 대응하는 소켓 채널 객체를 생성하고 빈 채널 파이프라인 객체를 생성하여 소켓 채널에 할당한다.
    // 2. 소켓 채널에 등록된 ChannelInitializer 인터페이스의 구현체를 가져와서 initChannel 메서드를 호출한다.
    // 3. 소켓 채널 참조로부터 1에서 등록한 파이프라인 객체를 가져오고 채널 파이프라인에 입력된 이벤트 핸들러의 객체를 등록한다.

    // 위와 같은 단계가 완료되면 채널이 등록됐다는 이벤트가 발생하고 이때부터 클라이언트와 서버 간의 데이터 송수신을 위한 이벤트 처리가 시작된다.
}