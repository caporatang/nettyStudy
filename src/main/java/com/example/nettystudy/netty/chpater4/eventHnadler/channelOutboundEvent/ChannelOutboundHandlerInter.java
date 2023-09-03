package com.example.nettystudy.netty.chpater4.eventHnadler.channelOutboundEvent;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelOutboundEvent
 * fileName : Bind
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class ChannelOutboundHandlerInter {

    // 1. bind
    // 서버 소켓 채널이 클라이언트의 연결을 대기하는 IP와 포트가 설정되었을 때 발생한다.
    // bind 이벤트에서는 서버 소켓 채널이 사용중인 SocketAddress 객체가 인수로 입력된다.
    // 서버 소켓 채널이 사용하는 IP와 포트 정보를 확인할 수 있다.

    // 2. connect
    // connect 이벤트는 클라이언트 소켓 채널이 서버에 연결되었을 때 발생한다.
    // connect 이벤트에서는 원격지의 SocketAddress 정보와 로컬 SocketAddress 정보가 인수로 입력된다.
    // 원격지의 연결을 생성할 때 로컬 SocketAddress 정보를 입력하지 않았다면 이 이벤트에서 수신한 로컬 SocketAddress는 null이다.

    // 3. disconnect
    // disconnect 이벤트는 클라이언트 소켓 채널의 연결이 끊어졌을 때 발생. -> 별도의 인수 없음

    // 4. close
    // close 이벤트는 클라이언트 소켓 채널의 연결이 닫혔을 때 발생한다. -> 별도의 인수 없음

    // 5. write
    // write 이벤트는 소켓 채널에 데이터가 기록되었을 때 발생한다.
    // write 이벤트에는 소켓 채널에 기록된 데이터 버퍼가 인수로 입력된다.

    // 6. flush
    // flush 이벤트는 소켓 채널에 대한 flush 메서드가 호출되었을 때 발생 -> 별도의 인수 없음
}