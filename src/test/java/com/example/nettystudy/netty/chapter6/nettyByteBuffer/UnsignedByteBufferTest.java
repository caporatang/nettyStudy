package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

import com.example.nettystudy.netty.chapter6.ByteBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.nettystudy.netty.chapter6.nettyByteBuffer
 * fileName : UnsignedByteBufferTest
 * author : taeil
 * date : 2023/09/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/20        taeil                   최초생성
 */
public class UnsignedByteBufferTest {
    // 부호 없는 값 읽기
    // 자바에는 C언와 달리 부호 없는 데이터형이 없다. -> 모든 원시 데이터형은 모두 부호 있는 데이터로 간주되고 저장된다.
    // 예를들어 부호가 있는 2바이트 Short 데이터형에 저장 가능한 값의 범위는 -32.768부터 32.767 까지다.
    // 자바 애플리케이션이 C언어로 작성된 애플리케이션과 네트워크 통신할 때 부호 없는 데이터를 사용하기도 하는데 자바에서 부호 없는 값을 처리하는것은 까다로운 작업에 속한다.

    // 자바에서 1바이트 데이터를 부호 없는 데이터로 변환하는 방법은 2바이트 데이터형에 데이터를 저장하는 것이다.
    // 부호 없는 데이터를 읽을 때는 읽을 데이터보다 큰 데이터형에 할당한다.

    final String source = "Hello world";

    @Test
    void unsignedBufferToJavaBuffer() {
        ByteBuf buf = Unpooled.buffer(11);
        buf.writeShort(-1); // 빈 바이트 버퍼에 음수 1을 기록

        // -1은 부호 있는 16진수 표기법에서 0XFFFF
        // -2는 0xFFFE
        // 0xFFFF를 부호 없는 정수로 표현하면 65535가 된다.

        // 0번째 바이트부터 2바이트를 읽어서 4바이트 데이터인 int로 읽어들이면 65535가 된다.
        assertEquals(65535, buf.getUnsignedShort(0));
    }

    // 네티 바이트 버퍼는 저장된 데이터의 부호 없는 값을 읽는 getUnsignedXXX 계열의 메서드를 제공한다
    // 각 메서드의 응답은 읽을 데이터형보다 한 단계 더 큰 데이터형이다.
    // 예를 들어 2바이트 데이터를 읽는 getUnsignedShort 메서드의 응답 데이터형은 4바이트 데이터형인 int다.

    // 메서드               원본 데이터형      리턴 데이터형
    // getUnsignedByte      byte            short
    // getUnsignedShort     short           int
    // getUnsignedMedium    medium          int
    // getUnsignedInt       int             long

}