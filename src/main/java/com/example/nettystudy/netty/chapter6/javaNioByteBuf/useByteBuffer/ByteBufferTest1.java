package com.example.nettystudy.netty.chapter6.javaNioByteBuf.useByteBuffer;


import java.nio.ByteBuffer;

/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf
 * fileName : ByteBufferTest1
 * author : taeil
 * date : 2023/09/13
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/13        taeil                   최초생성
 */
public class ByteBufferTest1 {
    public static void main(String[] args) {
        ByteBuffer firstBuffer = ByteBuffer.allocate(11);

        // 11바이트를 저장할 수 있는 초기화된 바이터 버퍼의 position, limit, capacity 속상값 출력
        System.out.println("바이트 버퍼 초깃값 : " + firstBuffer);

        // 바이트 버퍼 객체는 데이터 저장을 위한 put 메서드에 자바의 기본형 데이터만을 입력할 수 있다.
        // 바이트 버퍼에 저장할 "Hello world" 문자열 데이터를 바이트 배열로 변환한다.
        byte[] source = "Hello world".getBytes();

        // 11바이트 크기의 바이트 버퍼에 11바이트 문자열 데이터를 저장한다.
        firstBuffer.put(source);
        System.out.println("11 바이트 기록 후 " + firstBuffer);
    }
}