package com.example.nettystudy.netty.chapter6;

/**
 * packageName : com.example.nettystudy.netty.chapter6
 * fileName : ByteBufferPooling
 * author : taeil
 * date : 2023/09/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/20        taeil                   최초생성
 */
public class ByteBufferPooling {
    // 바이트 버퍼 풀링
    // 자바의 바이트 버퍼는 언어 자체에서 제공하는 버퍼 풀이 없다. -> 바이트 버퍼 풀을 사용하려면 객체 풀링을 제공하는 서드파티 라이브러리를 사용하거나 직접 구현해야 한다.
    // 네티는 프레임워크에서 바이트 버퍼 풀을 제공하고 이씅며 다이렉트 버퍼와 힙 버퍼를 모두 풀링할 수 있다.

    // 바이트 버퍼 풀링을 사용함으로써 얻을 수 있는 가장 큰 장점은 버퍼를 빈번히 할당하고 해제할 때 일어나는 가비지 컬렉션 횟수의 감소다.
    // 네티 바이트 버퍼 풀링은 ByteBufAllocator를 사용하여 바이트 버퍼를 생성할 때 자동으로 수행된다.

    // 네티는 바이트 버퍼를 풀링하기 위해서 바이트 버퍼에 참조 수를 기록한다.
    // 네티는 바이트 버퍼의 참조 수를 관리하기 위하여 ReferenceCountUtil 클래스에 정의된 retain 메서드와 release 메서드를 사용한다.
    // retain 메서드는 참조 수를 증가시키고, release 메서드는 참조수를 감소시킨다.

    // 바이트 버퍼의 release 메서드를 호출하면 바이트 버퍼의 참조 수가 1 줄어들고 할당된 메모리가 해제된다.
    // 만약 이미 참조 수가 0일 때 release 메서드를 호출하면 IllegalReferenceCountException이 발생한다.
}