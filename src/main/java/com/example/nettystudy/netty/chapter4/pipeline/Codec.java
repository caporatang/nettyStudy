package com.example.nettystudy.netty.chapter4.pipeline;

/**
 * packageName : com.example.nettystudy.netty.chpater4
 * fileName : Codec
 * author : taeil
 * date : 2023/08/31
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/31        taeil                   최초생성
 */
public class Codec {
    // 채널 파이프라인ㄴ은 채널에서 발생한 이벤트가 이동하는 통로
    // 이 통로를 통해서 이동하는 이벤트를 처리하는 클래스를 이벤트 핸들러라고 하며 이벤트 핸들러를 상속받아서 구현한 구현체들을 '코덱' 이라고한다.

    // 자주 사용되는 이벤트 핸들러를 미리 구현해둔 코덱 묶음은 io.netty.handler.codec 패키지에 위치해 있다.
    // 대표적인 코덱으로 HTTP 코덱이 있다.
}