package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName : com.example.nettystudy.netty.chapter6.nettyByteBuffer
 * fileName : OrderedByteBufferTest
 * author : taeil
 * date : 2023/09/20
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/20        taeil                   최초생성
 */
public class OrderedByteBufferTest {
    // 엔디안 변환
    // 네티의 바이트 버퍼의 기본 엔디안은 자바와 동일하게 빅엔디안이다.
    // 리틀 엔디안의 바이트 버퍼가 필요한데 이때 바이트 버퍼의 order 메서드를 사용하여 엔디안을 변환한다.

    // 엔디안이란?
    // 컴퓨터 메모리와 같은 1차원의 공간에 여러 개의 연속된 대상을 배열하는 방법을 뜻하며, 바이트를 배열하는 방법을 바이트 순서(Byte order)라 한다.
    // 엔디안은 보통 큰 단위가 앞에 나오는 빅 엔디안, 작은 단위가 앞에 나오는 리틀 엔디안, 두 경우에 속하지 않거나 둘을 모두 지원하는 것을 미들 엔디안이라 부른다.

    @Test
    void pooledHeapBufferTest(){
        ByteBuf buf = Unpooled.buffer();
        // 생성한 네티 바이트 버퍼의 엔디안이 빅 엔디안인지 확인한다.
        assertEquals(ByteOrder.BIG_ENDIAN, buf.order());

        // 바이트 버퍼에 숫자 1을 2바이트 Short형으로 기록한다. 바이트 버퍼의 기본 엔디안은 빅엔디안이므로 0x0001이 저장된다.
        buf.writeShort(1);

        // markReaderIndex 메서드로 현재 바이트 버퍼의 읽기 인덱스 위치를 표시한다. -> markReaderIndex 메서드로 표시한 읽기 인덱스 위치로 돌아가려면 resetReaderIndex를 사용
        buf.markReaderIndex();
        // 2에서 저장한 데이터가 1인지 확인한다 -> 빅엔디안으로 저장된 데이터를 그대로 읽는다.
        assertEquals(1, buf.readShort());

        // 읽기 인덱스의 위치를 markReaderIndex를 사용하여 표시한 위치로 이동시킨다.
        buf.resetReaderIndex();

        // 바이트 버퍼의 order 메서드로 리틀엔디안의 바이트 버퍼를 생성
        // 생성된 바이트 버퍼는 바이트 버퍼 내부의 배열과 읽기 인덱스, 쓰기 인덱스를 공유한다. 내용은 동일하지만 리틀엔디안에 해당하는 읽기 쓰기 메서드를 제공하는 바이트 버퍼 객체를 얻을 수 있다.
        ByteBuf lettleEndianBuf = buf.order(ByteOrder.LITTLE_ENDIAN);

        // 리틀엔디안에 해당하는 2바이트 Short형 데이터를 읽고 그 값이 256인지 확인한다 빅엔디안인 0x0001을 리틀엔디안으로 변환하면 0x0100이 되므로 십진수 256이 된다.
        assertEquals(256, lettleEndianBuf.readShort());


        // 주의점 : 네티 바이트 버퍼의 Order 메서드는 새로운 바이트 버퍼를 생성하는 것이 아니라 주어진 바이트 버퍼의 내용을 공유하는 파생 바이트 버퍼 객체를 생성하므로 유의 해야한다.
    }
}