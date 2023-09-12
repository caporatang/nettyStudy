package com.example.nettystudy.netty.chapter6.javaNioByteBuf;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

import static org.junit.jupiter.api.Assertions.*;

class CreateByteBufferTestTest {

    @Test
    public void createTest() {
        CharBuffer heapBuffer = CharBuffer.allocate(11); // 11개의 char형 데이터를 저장할 수 있는 힙 버퍼를 생성
        assertEquals(11, heapBuffer.capacity());
        assertFalse(heapBuffer.isDirect()); // 다이렉트 버퍼인지 체크

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(11); // 11개의 byte 데이터를 저장할 수 있는 다이렉트 버퍼를 생성
        assertEquals(11, directBuffer.capacity());
        assertTrue(directBuffer.isDirect());

        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0 };
        IntBuffer intHeapBUffer = IntBuffer.wrap(array); // 인트 배열을 감싸는 버퍼를 생성한다 -> 이때 생성된 버퍼는 JVM의 힙 영역에 생성된다.
        assertEquals(11, intHeapBUffer.capacity());
        assertEquals(false, intHeapBUffer.isDirect());

    }

}