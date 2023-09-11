package com.example.nettystudy.netty.chapter5.nettyFuture;

/**
 * packageName : com.example.nettystudy.netty.chapter5.nettyFuture
 * fileName : NettyFuturePattern
 * author : taeil
 * date : 2023/09/11
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/11        taeil                   최초생성
 */
public class NettyFuturePattern {
    // 네티는 비동기 호출을 위한 두 가지 패턴을 제공한다.
    // 첫 번쨰는 리액터 패턴의 구현체인 이벤트 핸들러
    // 두 번째는 2장에서 언급한 퓨처 패턴이다.

    // 퓨처 패턴은 미래에 완료될 작업을 등록하고 처리 결과를 확인하는 객체를 통해서 작업의 완료를 확인하는 패턴이다.
    // 퓨처 패턴은 메서드를 호출하는 즉시 퓨처 객체를 돌려준다.
    // 메서드의 처리 결과는 나중에 Future 객체를 통해서 확인한다.
}