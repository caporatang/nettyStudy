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

    @Test
    public void test() {
        // rewind() 메서드의 사용

        ByteBuffer firstBuffer = ByteBuffer.allocate(111);
        System.out.println("초기 상태 :"  + firstBuffer);

        firstBuffer.put((byte) 1);
        firstBuffer.put((byte) 2);
        assertEquals(2, firstBuffer.position());

        // rewind 메서드를 사용하면 position 속성을 0으로 변경하게 된다.
        firstBuffer.rewind();
        assertEquals(0, firstBuffer.position());

        // put 메서드를 사용한다면 position이 증가하여 첫번째 값을 조회할수 없지만 위에서 처럼 rewind 하게 되면 첫번쨰 값을 조회할수있다.
        assertEquals(1, firstBuffer.get());
        // 다시 조회하면 포지션 속성이 증가한다.
        assertEquals(1, firstBuffer.position());

        System.out.println(firstBuffer);
    }

    @Test
    public void writeByteBufferTest() {
        // 자바는 put, get 메서드가 호출된 이후의 position 정보를 저장하기 위하여 flip 메서드를 제공한다.

        //byte형 11개를 저장할 수 있는 다이렉트 버퍼를 생성한다.
        ByteBuffer firstBuffer = ByteBuffer.allocate(11);

        // 생성된 firstBuffer의 position 속성이 0 인지 검사
        assertEquals(0, firstBuffer.position());
        // 생성된 firstBuffer의 limit 속성이 11인지 검사
        assertEquals(11, firstBuffer.limit());

        // 바이트 버퍼에 부터 4까지 4개의 데이터를 기록
        firstBuffer.put((byte) 1);
        firstBuffer.put((byte) 2);
        firstBuffer.put((byte) 3);
        firstBuffer.put((byte) 4);

        // 데이터를 기록한 이후의 position 속성값이 4인지 확인 -> 한 바이트를 저장하는 put 메서드가 4번 호출되었기 때문에 position 속성값이 4다.
        // position 속성값이 저장된 바이트 길이가 아니라 저장된 요소 개수가 '4' 다!
        assertEquals(4, firstBuffer.position());
        // limit 속성이 초기 생성값과 동일하게 11인지 확인한다.
        assertEquals(11, firstBuffer.limit());

        // flip 메서드 호출
        firstBuffer.flip();
        // flip 메서드 호출 이후의 position 속성값이 0으로 변경되었는지 확인한다.
        assertEquals(0, firstBuffer.position());
        // flip 메서드 호출 이후의 limit 속성값이 4로 변경되었는지 확인한다.
        assertEquals(4, firstBuffer.limit());

        // 데이터를 기록한 이후에 flip 메서드를 호출하면 limit 속성값이 마지막에 기록한 데이터의 위치로 변경된다.
    }

    @Test
    public void bufferRead() {

        // byte형 11개가 저장된 tempArray 바이트 배열을 사용하여 바이트 버퍼 객체를 생성한다.
        byte[] tempArray = {1,2,3,4,5,0,0,0,0,0,0};
        ByteBuffer firstBuffer = ByteBuffer.wrap(tempArray);

        assertEquals(0, firstBuffer.position());
        assertEquals(11, firstBuffer.limit());

        // 데이터 조회
        assertEquals(1, firstBuffer.get());
        assertEquals(2, firstBuffer.get());
        assertEquals(3, firstBuffer.get());
        assertEquals(4, firstBuffer.get());
        assertEquals(4, firstBuffer.get());
        assertEquals(11, firstBuffer.get());

        // flip
        firstBuffer.flip();
        assertEquals(0, firstBuffer.position());
        assertEquals(4, firstBuffer.limit());

        // 세 번째 요소의 값을 조회
        firstBuffer.get(3);

        // get 메서드를 호출한 이후의 position 메서드가 0인지 확인
        assertEquals(0, firstBuffer.position());

        // 바이트 버퍼에 읽거나 쓰기 작업을 수행한 이후에 호출되는 flip 메서드는 이전에 작업한 마지막 위치를 limit 속성으로 변경한다.
        // flip 메서드는 쓰기 작업 완료 이후에 데이터의 처음부터 읽을 수 있도록 현재 포인터의 위치를 변경하여 읽기에서 쓰기 또는 쓰기에서 읽기로 작업을 전환할 수 있게 된다.
        // 하나의 바이트 버퍼에 대하여 읽기 작업 또는 쓰기 작업의 완료를 의미하는 flip 메서드를 호출하지 않으면 반대 작업을 수행할 수 없다.
    }

















}