package com.example.nettystudy.netty.chapter6.javaNioByteBuf.useByteBuffer;

import java.nio.ByteBuffer;

/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf.useByteBuffer
 * fileName : ByteBufferTest3
 * author : taeil
 * date : 2023/09/13
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/13        taeil                   최초생성
 */
public class ByteBufferTest3 {
    // 해당 코드는 firstBuffer의 바이트 버퍼에 이 입력되고 출력 결과는 1이 출력될거처럼 보이지만 실제로는 그렇게 동작하지 않는다.
    // 바이트 버퍼의 위치 정보인 postion 속성 때문이다.
    // position 속성값은 바이트 버퍼의 put 메서드가 실행될 때 저장된 데이터의 길이만큼 증가하는데, get 메서드가 실행될 때도 읽어들인 길이만큼 증가하기 때문이다.
    public static void main(String[] args) {
        ByteBuffer firstBuffer = ByteBuffer.allocate(11);
        System.out.println("초기 상태 : " + firstBuffer); // 실행 -> position++

        firstBuffer.put((byte) 1);
        System.out.println(firstBuffer.get()); // get 메서드는 현재 position 속성이 지시하는 위치에서 데이터를 읽기 때문에 두 번째 요소의 초깃값인 0이 출력된다. -> position ++
        System.out.println(firstBuffer);
    }
}