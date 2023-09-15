package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

/**
 * packageName : com.example.nettystudy.netty.chapter6.nettyByteBuffer
 * fileName : createByteBuffer
 * author : taeil
 * date : 2023/09/15
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/15        taeil                   최초생성
 */
public class createByteBuffer {
    // 네티 바이트 버퍼는 자바 바이트 버퍼와 달리 프레임워크 레벨의 바이트 버퍼 풀을 제공한다
    // 이를 통해 생성된 바이트 버퍼를 재사용한다.

    // 네티의 바이트 버퍼를 바이트 버퍼 풀에 할당하려면 ByteBUfAllocator 인터페이스를 사용한다. -> ByteBufAllocator의 하위 추상 구현체인 pooledByteBufAllocator 클래스로 각 바이트 버퍼를 생서ㅓㅇ한다.

    // 네티 바이트 버퍼를 생성할 떄는 두 가지를 성택해야 한다.
    // 1. 풀링 여부
    // 2. 다이렉트 버퍼 여부

    // 네티 바이트 버퍼의 종류
    //                   풀링 함                      풀링 안 함
    //  힙 버퍼        PooledHeapByteBuf         UnpooledHeapByteBUf
    // 다이렉트 버퍼     PooledDirectByteBuf       UnpooledDirectByteBUf

    // 네티 바이트 버퍼 생성 방법
    //                                  풀링 함                                풀링 안 함
    //  힙 버퍼            ByteBufAllocator.DEFAULT.heapBuffer()            Unpooled.buffer()
    // 다이렉트 버퍼         ByteBufAllocator.DEFAULT.directBuffer()          Unpooled.directBuffer()

}