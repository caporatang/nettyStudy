package com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent
 * fileName : ChannelRegistered
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class ChannelRegistered {
    // 채널이 이벤트 루프에 등록되었을 때 발생

    // 이벤트 루프는 네티가 이벤트를 실행하는 스레드로써 부트 스트랩에 설정한 이벤트 루프다
    // channelRegistered 이벤트는 클라이언트와 서버에서 발생 위치가 조금 다르다.

    // 제일 처음 서버 소켓 채널을 생성할 때와 새로운 클라이언트가 서버에 접속하여 클라이언트 소켓 채널이 생성될 때 channelRegistered 이벤트가 발생한다.
    // 즉, 서버 소켓 채널에서 발생한 channelRegistered 이벤트와 서버에 연결된 클라이언트 소켓 채널에서 발생한 channelRegistereed 이벤트다.

    // 클라이언트에서는 서버 접속을 위한 connect 메서드를 수행할 때 channelRegistered 이벤트가 발생한다.
    // channelRegistered 이벤트는 서버와 클라이언트에 상관없이 새로운 채널이 생성되는 시점에 발생한다.
    
}