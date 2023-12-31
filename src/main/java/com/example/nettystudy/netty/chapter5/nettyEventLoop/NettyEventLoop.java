package com.example.nettystudy.netty.chapter5.nettyEventLoop;

import io.netty.util.concurrent.SingleThreadEventExecutor;

/**
 * packageName : com.example.nettystudy.netty.chapter5.nettyEventLoop
 * fileName : NettyEventLoop
 * author : taeil
 * date : 2023/09/09
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/09        taeil                   최초생성
 */
public class NettyEventLoop {
    // 네티의 이벤트 루프

    // 네티는 단일 스레드 이벤트 루프와 다중 스레드 이벤트 루프를 모두 사용할 수 있다.
    // 다중 스레드 이벤트 루프는 이벤트의 발생 순서와 실행 순서가 일치하지 않는다.
    // 그런데 네티에서는 이벤트 루프의 종류에 상관없이 이벤트 발생 순서에 따른 실행 순서를 보장한다.


    // 다중 스레드 이벤트 루프에서 이벤트의 발생 순서와 실행 순서가 일치하지 않는 이유는
    // 예를 들어 파일의 데이터를 읽어서 연결된 소켓 채널로 데이터를 전송하고 소켓 채널을 닫는 애플리케이션을 작성한다고 가정하면,
    // 애플리케이션에서 처리해야 하는 순서는
    // 1. 읽어들일 파일을 열어서 데이터를 버퍼로 읽어들이고 파일 닫기 이벤트를 발생시킨다.
    // 2. 읽어들인 데이터를 채널에 기록하고 기록 완료 이벤트를 발생시킨다.
    // 3. 데이터를 기록한 소켓 채널을 닫는 이벤트를 발생시킨다.
    // 위와 같은 이벤트가 순서대로 발생할 때 이벤트 처리 순서가 뒤 바뀌면 데이터가 완전히 기록되기 전에 소켓 채널이 닫히는 현상이 발생한다.

    // 위와 같은 동작을 네티는 처리 순서가 뒤바뀌지 않게 처리할수있는 이유
    // 1. 네티의 이벤트는 '채널'에서 발생한다.
    // 2. 이벤트 루프 객체는 이벤트 큐를 가지고 있다
    // 3. 네티의 채널은 하나의 이벤트 루프에 등록된다.

    // 네티의 각 채널은 개별 이벤트 루프 스레드에 등록된다.
    // 채널에서 발생한 이벤트는 항상 동일한 이벤트 루프 스레드에서 처리하여 이벤트 발생 순서와 처리 순서가 일치된다.

    // 이벤트의 수행 순서가 일치하지 않는 근본적인 이유는 이벤트 루프들이 이벤트 큐를 공유하기 때문에 발생되는데, 네티는 이벤트 큐를 이벤트 루프 스레드의 내부에 둠으로써 수행 순서 불일치의 원인을 제거한다.
    // 하나의 이벤트 루프 스레드는 여러 채널을 등록할 수 있다.
    // 이와 같은 구조는 다중 채널에 대한 효율적인 스레드 구조를 만들어낸다.
    // 여러 채널이 이벤트 루프에 등록되었을 때에도 이벤트 처리는 항상 발생 순서와 일치한다.
    // 즉, 처리를 위한 이벤트 루프 스레드가 하나이므로 이벤트 처리 순서는 이벤트 발생 순서와 같다.

    // 네티는 이벤트를 처리하기 위하여 SingleThreadEventExecutor를 사용한다.


}