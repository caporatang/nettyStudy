package com.example.nettystudy.netty.chapter6.javaNioByteBuf;

/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf
 * fileName : CreateByteBufferTest
 * author : taeil
 * date : 2023/09/12
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/12        taeil                   최초생성
 */
public class CreateByteBufferTest {
    // 바이트 버퍼는 데이터형에 따른 추상 클래스의 팩토리 메서드를 통해서 생성한다.
    // 바이트 버퍼를 생성하는 메서드는 모두 세 가지가 존재하며 각 메서드는 다음과 같다.

    // 1. allocate : JVM의 힙 영역에 바이트 버퍼를 생성한다. 메서드의 인수는 생성할 바이트 버퍼 크기이며 capacity 속성값이다. 또한 생성되는 바이트 버퍼의 값은 모두 0으로 초기화된다.
    // 2. allocateDirect : JVM의 힙 영역이 아닌 운영체제의 커널 영역에 바이트 버퍼를 생성한다.
            // allocateDirect 메서드는 ByteBuffer 추상 클래스만 사용할 수 있다. -> 다이렉트 버퍼는 ByteBuffer로만 생성할 수 있따.
            // 메서드의 인수는 생성할 바이트 버퍼의 크기이며 capacity 속성값이다.
            // 생성되는 바이트 버퍼의 값은 모두 0으로 초기화된다.
    // 3. wrap : 입력된 바이트 배열을 사용하여 바이트 버퍼를 생성한다. 입력에 사용된 바이트 배열이 변경되면 wrap 메서드를 사용해서 생성한 바이트 버퍼의 내용도 변경된다.

    // allocate 메서드를 사용하여 생성한 버퍼를 힙 버퍼라고 부른다.
    // allocateDirect 메서드를 통해서 생성된 버퍼를 다이렉트 버퍼라고 부른다.
    // 다이렉트 버퍼는 힙 버퍼에 비해서 생성시간은 길지만 더 빠른 읽기 쓰기 성능을 제공한다.
}