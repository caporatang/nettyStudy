package com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent
 * fileName : ChannelActive
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class ChannelActive {
    // channelActive 이벤트는 channelRegistered 이벤트 이후에 발생한다.
    // 채널이 생성되고 이벤트 루프에 등록된 이후에 네티 API를 사용하여 채널 입출력을 수행할 상태가 되었음을 알려주는 이벤트다.

    // 이 이벤트를 사용하기 적합한 작업의 예는 다음과 같다.
    // 1. 서버 애플리케이션에 연결된 클라이언트의 연결 개수를 count
    // 2. 서버 애플리케이션에 연결된 클라이언트에게 최초 연결에 대한 메시지 전송할 때
    // 3. 클라이언트 애플리케이션이 연결된 서버에 최초 메시지를 전달할 때
    // 4. 클라이언트 애플리케이션에서 서버에 연결된 상태에 대한 작업이 필요할 때

    // channelActive 이벤트는 서버 또는 클라이언트가 상대방에 연결한 직후에 한 번 수행할 작업을 처리하기에 적합하다.
}