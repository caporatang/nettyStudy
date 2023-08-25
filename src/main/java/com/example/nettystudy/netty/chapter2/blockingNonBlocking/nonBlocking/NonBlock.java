package com.example.nettystudy.netty.chapter2.blockingNonBlocking.nonBlocking;

/**
 * packageName : com.example.nettystudy.netty.chapter2.blockingNonBlocking.nonBlocking
 * fileName : NonBlock
 * author : taeil
 * date : 2023/08/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/25        taeil                   최초생성
 */
public class NonBlock {
    // 블로킹 소켓은 특정 메서드 같은 입출력 메서드가 호출되면 처리가 완료될 때까지 스레드가 멈춘다
    // 이러한 단점을 해결하는  방식이 논블로킹 소켓이다.

    // 논블로킹 모드의 소켓에서 데이터를 읽는 명령인 read 메서드를 호출했다고 가정해보자.
    // read 메서드의 처리 결과는 소켓에서 읽어들인 바이트 길이다.
    // 만약 클라이언트가 데이터를 아직 전송하지 않았거나 데이터가 수신버퍼까지 도달하지 않았다면 read 메서드는 읽어들인 바이트 길이인 0을 돌려준다.
}