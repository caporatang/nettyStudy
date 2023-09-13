package com.example.nettystudy.netty.chapter6.javaNioByteBuf.useByteBuffer;

import java.nio.ByteBuffer;

/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf.useByteBuffer
 * fileName : ByteBufferTest2
 * author : taeil
 * date : 2023/09/13
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/13        taeil                   최초생성
 */
public class ByteBufferTest2 {
    public static void main(String[] args) {
        ByteBuffer firstBuffer = ByteBuffer.allocate(11);

        // 11바이트를 저장할 수 있는 초기화된 바이트 버퍼의 position, limit, capacity 속성값을 출력한다.
        System.out.println("초기 상태 : " + firstBuffer);

        byte[] source = "Hello world".getBytes();

        // 바이트 버퍼에 입력할 데이터인 "Hello world!" 데이터의 바이트 길이만큼 반복 수행한다.
        for (byte item : source) {
            // ByteBuffer 클래스의 put(byte b) 메서드는 바이트 버퍼에 한 바이트를 기록하고 position의 위치를 1 증가시킨다.
            // 출력하면서 position 위치를 확인할수있다.
            firstBuffer.put(item);
            System.out.println("현재 상태 : " + firstBuffer);
        }

    }
}