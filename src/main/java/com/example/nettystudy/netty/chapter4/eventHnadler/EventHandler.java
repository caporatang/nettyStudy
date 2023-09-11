package com.example.nettystudy.netty.chapter4.eventHnadler;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler
 * fileName : EventHandler
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class EventHandler {
    // 네티는 비동기 호출을 지원하는 두 가지 패턴을 제공한다.
    // 1. 퓨처 패턴
    // 2. 리액터 패턴의 구현체인 이벤트 핸들러

    // 이벤트 핸들러는 네티의 소켓 채널에서 발생한 이벤트를 처리하는 인터페이스다.
    // 소켓 채널의 이벤트를 인터페이스로 정의하고, 인터페이스를 상속받은 이벤트 핸들러를 작성하여 채널 파이프라인에 등록한다.
    // 채널 파이프라인으로 입력되는 이벤트를 이벤트 루프가 가로채어 이벤트에 해당하는 메서드를 수행하는 구조.
}