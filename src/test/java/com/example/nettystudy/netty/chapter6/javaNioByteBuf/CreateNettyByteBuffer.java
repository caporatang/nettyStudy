package com.example.nettystudy.netty.chapter6.javaNioByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;
/**
 * packageName : com.example.nettystudy.netty.chapter6.javaNioByteBuf
 * fileName : CreateNettyByteBuffer
 * author : taeil
 * date : 2023/09/15
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/15        taeil                   최초생성
 */
public class CreateNettyByteBuffer {
    // 버퍼를 생성할떄 꼭 인수에 생성할 바이트 버퍼의 크기를 지정하지 않고 호출도 가능하다. -> 네티의 지정된 기본값 256 바이트 크기의 바이트 버퍼가 생성된다.
    
    @Test
    void createUnpooledHeapBufferTest() {
        // 바이트 버퍼 풀을 사용하지 않는 11바이트 크기의 힙 버퍼 생성
        ByteBuf buf = Unpooled.buffer(11);

        // 버퍼가 정상인지 체크
        testBuffer(buf, false);
    }

    @Test
    void createUnpooledDirectBufferTest() {
        // 바이트 버퍼 풀을 사용하지 않는 11바이트 크기의 다이렉트 바퍼 생성
        ByteBuf buf = Unpooled.directBuffer();
        testBuffer(buf, true);
    }

    @Test
    void createPooledHeapBufferTest() {
        // 풀링된 11바이트 크기 힙 버퍼 생성
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(11);
        testBuffer(buf, false);
    }

    @Test
    void createPooledDirectBufferTest() {
        // 풀링된 11바이트 크기 다이렉트 버퍼 생성
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(11);
        testBuffer(buf, true);
    }


    private void testBuffer(ByteBuf buf, boolean isDirect) {
        assertEquals(11, buf.capacity()); // 버퍼 크기 체크
        assertEquals(isDirect, buf.isDirect()); // 다이렉트 버퍼 체크

        // 주어진 바이트 버퍼에서 읽을 수 있는 바이트 수가 0인지 확인.
        // 네티 바이트 버퍼의 초기 속성값은 읽기 인덱스와 쓰기 인덱스 모두 0이다.
        // 읽을 수 있는 바이트 수는 0이 되며 쓸 수 있는 바이트 수는 바이트 버퍼의 크기인 11이다.
        assertEquals(0, buf.readableBytes());
        assertEquals(11, buf.writableBytes());
    }
}