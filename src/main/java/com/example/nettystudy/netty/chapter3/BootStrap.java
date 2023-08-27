package com.example.nettystudy.netty.chapter3;

/**
 * packageName : com.example.nettystudy.netty.chapter3
 * fileName : BootStrap
 * author : taeil
 * date : 2023/08/27
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/27        taeil                   최초생성
 */
public class BootStrap {
    // 네티로 네트워크 애플리케이션을 작성할 때 가장 기본이 되는 부트스트랩
    // 부트스태릅은 네티로 작성한 네트워크 애플리케이션이 시작할 때 가장 처음 수행되는 부분이다. -> 애플리케이션이 수행할 동작과 각종 설정을 지정하며 네티 애플리케이션의 기본이 된다.

    // 부트스트랩 설정은 크게 이벤트 루프, 채널의 전송 모드, 채널 파이프라인으로 나뉜다.
    // 이벤트 루프 : 소켓 채널에서 발생한 이벤트를 처리하는 스레드 모델에 대한 구현이 담겨 있다.
    // 설정한 소켓의 모드에 따라 사용되는 이벤트 루프의 구현체가 달라지기도 하는데 사용할 소켓 채널의 모드에 따라서 블로킹, 논블로킹 그리고 epoll로 지정할 수 있다.
    // epoll : 입출력 다중화 기법으로써 현재까지 알려진 입출력 방식 중에 가장 빠르다
    // 채널 파이프라인 : 소켓 채널로 수신된 데이터를 처리할 데이터 핸들러들을 지정하고, 연결된 채널에서 사용할 데이터 핸들러에 대한 내용을 포함한다.

    
}