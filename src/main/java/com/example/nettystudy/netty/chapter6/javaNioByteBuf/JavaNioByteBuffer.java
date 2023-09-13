package com.example.nettystudy.netty.chapter6.javaNioByteBuf;

import java.nio.*;

/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf
 * fileName : JavaNioByteBuffer
 * author : taeil
 * date : 2023/09/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/12        taeil                   최초생성
 */
public class JavaNioByteBuffer {
    // 자바 NIO 바이트 버퍼는 바이트 데이터를 저장하고 읽는 저장소다.
    // 배열을 맴버 변수로 가지고 있으며 배열에 대한 읽고 쓰기를 추상화한 메서드를 제공한다.
    // 추상화한 메서드로 인해서 개발자가 배열의 인덱스에 대한 계산 없이 데이터의 변경 처리를 수행할 수 있다.

    // 자바에서 제공하는 버퍼로는
    // ByteBuffer, CharBuffer, IntBuffer, ShortBuffer, LongBuffer, FloatBuffer, DoubleBuffer 가 있으며 각 바이트 버퍼는 저장되는 데이터형에 따라서 적당한 클래스를 선택해서 사용한다.

    // 바이트 버퍼 클래스는 내부의 배열 상태를 관리하는 세 가지 속성을 가지고 있는데 각 속성은 다음과 같다.
    // 1. capacity : 버퍼에 저장할 수 있는 데이터의 최대 크기로 한 번 정하면 변경이 불가능하다. 이 값은 버퍼를 생성할 때 생성자의 인수로 입력한 값이다.
    // 2. position : 읽기 또는 쓰기가 작업 중인 위치를 나타낸다. 버퍼 객체가 생상될 때 0으로 초기화 되고 데이터를 입력하는 put 메서드나 데이터를 읽는 get 메서드가 호출되면 자동으로 증가하며, limit와 capacity 값보다 작거나 같다.
    // 3. limit : 읽고 쓸 수 있는 버퍼 공간의 최대치를 나타낸다. limit 메서드로 값을 조절할 수 있다. 이 값을 capacity 값보다 크게 설정할 수 없다.

    // 이 속성들은 각각 capacity, position, limit 메서드로 값을 읽을 수 있다.




    // -----------
    // 자바의 바이트 버퍼를 사용할 때는 읽기와 쓰기를 분리해서 생각해야 하며 특히 다중 스레드 환경에서 바이트 버퍼를 공유하지 않아야 한다.
    // 네티는 이와 같은 자바 바이트 버퍼의 문제점을 해결하기 위해서 읽기를 위한 인덱스와 쓰기를 위한 인덱스를 별도로 제공한다..!
}