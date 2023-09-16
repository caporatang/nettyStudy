package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

/**
 * packageName : com.example.nettystudy.netty.chapter6.nettyByteBuffer
 * fileName : UseByteBuffer
 * author : taeil
 * date : 2023/09/16
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/16        taeil                   최초생성
 */
public class UseNettyByteBuffer {
    // 네티 바이트 버퍼는 자바 바이트 버퍼와 달리 읽기 쓰기 전환에 flip 메서드를 호출하지 않는다.
    // 자바의 바이트 버퍼는 쓰기 작업이 끝나고 읽기 작업을 시작하기 전 또는 그 반대의 상황에서 항상 flip 메서드를 호출해야 한다.
    // 원인은 바이트 버퍼에서 사용하는 인덱스가 하나이기 때문인데, 네티는 읽기 인덱스와 쓰기 인덱스를 분리함으로써 이 같은 문제를 해결한다.
}