package com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent;

import io.netty.channel.ChannelInboundHandler;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent
 * fileName : ChannelInboundEvent
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class ChannelInboundEvent {
    // 네티는 소켓 채널에서 발생하는 이벤트를 인바운드 이벤트와 아웃바운드 이벤트로 추상화한다.
    // 인바운드 이벤트는 소켓 채널에서 발생한 이벤트 중에서 연결 상대방이 어떤 동작을 취했을 때 발생한다.
    // ex) 채널 활성화, 데이터 수신 등의 이벤트가 해당된다.

    // 클라이언트가 서버에 접속한 상태에서 서버로 데이터를 전송한다고 가정한다.
    // 서버의 관점에서는 데이터가 수신되는데,
    // 이때 네티는 소켓 채널에서 읽을 데이터가 있다는 이벤트를 채널 파이프라인으로 흘려보내고 채널 파이프라인에 등록된 이벤트 핸들러 중에서 인바운드 이벤트 핸들러가 해당 이벤트에 해당하는 메서드를 수행한다.
    // 네티는 인바운드 이벤트를 ChannelInboundHandler 인터페이스로 제공한다.

    // ChannelInboundHandler 의 인터페이스 정의다.
    // 각 메서드는 네티의 소켓 채널에서 인바운드 이벤트로 발생하는 이벤트에 대응된다.
    // 클라이언트가 서버에 접속하여 "hello" 라는 문자열 데이터를 전송하고 연결을 끊었다고 가정하면
    // 서버측의 소켓 채널(클라이언트 소켓 채널) 에서 발생하는 이벤트는
    // 1. 채널 등록
    // 2. 채널 활성화
    // 3. 데이터 읽기
    // 4. 데이터 읽기,
    // 5. 데이터 읽기 완료
    // 6. 채널 비활성화
    // 7. 채널 등록 해제
    // 위와 같은 순서로 발생하고, 이것을 이벤트 명으로 바꾸면 ChannelInboundHandler 에 정의된 메서드들과 같다.
}