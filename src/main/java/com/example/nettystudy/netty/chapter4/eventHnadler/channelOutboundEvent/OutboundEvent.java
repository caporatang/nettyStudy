package com.example.nettystudy.netty.chapter4.eventHnadler.channelOutboundEvent;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelOutboundEvent
 * fileName : OutboundEvent
 * author : taeil
 * date : 2023/09/03
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/03        taeil                   최초생성
 */
public class OutboundEvent {
    // 아웃바운드 이벤트는 소켓 채널에서 발생한 이벤트 중에서 네티 사용자(프로그래머)가 요청한 동작에 해당하는 이벤트를 말하며 연결 요청, 데이터 전송, 소켓 닫기 등이 이에 해당한다
    // 네티는 아웃바운드 이벤트를 ChannelOutboundHandler 인터페이스로 제공한다.
    // 모든 ChannelOutboundHanlder 이벤트는 ChannelHandlerContext 객체를 인수로 받는다.

    // ----------------------- ChannelHandlerContext 객체 -----------------------
    // ChannelHandlerContext는 두 가지 네티 객체에 대한 상호작용을 도와주는 인터페이스.
    // 1. 입출력에 대한 처리 : ChannelHandlerContext의 writeAndFlush 메서드로 채널에 데이터를 기록한다. 또는 ChannelHandlerContext의 close 메서드로 채널의 연결을 종료할 수 있다.
    // 2. 채널 파이프라인에 대한 상호작용 :
    // 2-1)
    // 채널 파이프라인에 대한 상호작용으로는 사용자에 의한 이벤트 발생과 채널 파이프라인에 등록된 이벤트 핸들러의 동적 변경이 있다.
    // 채널 파이프라인에는 여러 이벤트 핸들러를 등록할 수 있으며 채널이 초기화될 때 채널 파이프라인의 이벤트 핸들러가 설정된다.
    // ChannelHandlerContext는 채널이 초기화될 때 설정된 채널 파이프라인을 가져오는 메서드를 제공한다.
    // 즉, ChannelHandlerContext를 통해서 설정된 채널 파이프라인을 수정할 수 있다.

    // 2-2) 사용자에 의한 이벤트 발생.
    // 데이터 수신 이벤트 메서드에서 논리적인 오류가 발생했다고 가정
    // 오류를 처리하는 공통 로직이 이미 exceptionCaught 이벤트 메서드에 작성되어 있다면 ChannelHandlerContext의 fireExceptionCaught 메서드를 호출하면 된다.
    // 즉, fireExceptionCaught 메서드를 호출하면 채널 파이프 라인으로 exceptionCaught 이벤트가 전달되고 해당 이벤트 메서드가 수행된다.

}