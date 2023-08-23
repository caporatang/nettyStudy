package com.example.nettystudy.netty.chapter2.blockingNonBlocking;

/**
 * packageName : com.example.nettystudy.netty.chapter2
 * fileName : BlockNonBlock
 * author : taeil
 * date : 2023/08/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/24        taeil                   최초생성
 */
public class BlockNonBlock {
    // 블로킹과 논블로킹
    // 소켓의 동작 방식은 블로킹 모드와 논블로킹 모드로 나뉜다.
    // 블로킹은 요청한 작업이 성공하거나 에러가 발생하기 전까지는 응답을 돌려주지 않는 것을 말하며, 논블로킹은 요청한 작업의 성공 여부와 상관없이 바로 결과를 돌려주는 것을 말한다.
    // 이때 요청의 응답값에 의해서 에러나 성공 여부를 판단한다.

    // 자바에서는 JDK 1.3까지 블로킹 방식의 I/O 만을 지원했다.
    // 이후 JDK 1.4가 발표되면서 NIO (New I/O) 라는 논블로킹 I/O API가 추가되었다.
    // API의 이름에서 유추할 수 있듯이 입출력과 관련된 기능을 제공하는데, 소켓도 입출력 채널의 하나로서 NIO API를 사용할 수 있으며 NIO API를 통해서 블로킹과 논블로킹 모드의 소켓을 사용할 수 있다.

    // 자바는 두 가지 소켓을 구분하기 위해서 별도의 클래스를 사용한다.
    // 블로킹 소켓은 ServerSocket, Socket 클래스
    // 논블로킹 소켓은 ServerSocketChannel, SocketChannel 클래스를 사용한다.

}