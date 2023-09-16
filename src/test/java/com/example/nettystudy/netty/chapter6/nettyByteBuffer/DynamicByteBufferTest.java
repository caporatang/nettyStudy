package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.apache.logging.log4j.util.Chars;
import org.hibernate.ResourceClosedException;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * packageName : com.example.nettystudy.netty.chapter6.nettyByteBuffer
 * fileName : DynamicByteBufferTest
 * author : taeil
 * date : 2023/09/16
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/16        taeil                   최초생성
 */
public class DynamicByteBufferTest {
    // 자바 바이트 버퍼는 버퍼를 생성할 때 크기를 지정해야 하며 한 번 생성된 바이트 버퍼의 크기를 변경할 수 없다.
    // 네티 바이트 버퍼는 생성된 버퍼의 크기를 동적으로 변경할 수 있다.

    // 바이트 버퍼의 크기를 변경해도 저장된 데이터는 보존된다.

    @Test
    void createUnpooledHeapBufferTest() {
        ByteBuf buf = Unpooled.buffer(11);
        testBuffer(buf, false);
    }

    @Test
    void createUnpooledDirectBufferTest() {
        ByteBuf buf = Unpooled.directBuffer();
        testBuffer(buf, true);
    }

    @Test
    void createPooledHeapBufferTest() {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(11);
        testBuffer(buf, false);
    }

    @Test
    void createPooledDirectBufferTest() {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(11);
        testBuffer(buf, true);
    }

    private void testBuffer(ByteBuf buf, boolean isDirect) {
        assertEquals(11, buf.capacity());
        assertEquals(isDirect, buf.isDirect());

        String sourceData = "hello world";

        buf.writeBytes(sourceData.getBytes());         // 11바이트를 저장할 수 있는 바이트 버퍼에 "Hello world" 문자열을 저장한다.
        assertEquals(11, buf.readableBytes()); // 바이트 버퍼에서 읽어들일 수 있는 바이트 버퍼 크기가 11인지 확인. -> 바이트 버퍼의 readableBytes 메서드는 "Hello world" 문자열의 길이인 11을 돌려준다
        assertEquals(0, buf.writableBytes()); // 바이트 버퍼에 기록할 수 있는 남은 공간이 0인지 확인한다.

        assertEquals(sourceData, buf.toString(Charset.defaultCharset())); // 바이트 버퍼에 저장된 데이터를 문자열로 변환하고 그 결과가 " Hello world " 문자열과 같은지 확인한다.

        // 바이트 버퍼의 크기를 6으로 줄인다.
        // 네티의 바이트 버퍼는 capacity 메서드를 사용하여 바이트 버퍼의 크기를 동적으로 조절할 수 있다.
        // 저장된 데이터보다 작은 크기로 조절하면 나머지 데이터는 잘려진다.
        buf.capacity(6);
        assertEquals("hello", buf.toString(Charset.defaultCharset())); // 바이트에 저장된 "world" 문자열이 잘림
        assertEquals(6, buf.capacity());

        // 13 으로 크기를 늘림 -> 바이트 버퍼의 크기를 늘리면 이전에 저장된 데이터는 보존.
        buf.capacity(13);
        assertEquals("hello", buf.toString(Charset.defaultCharset())); // 데이터가 잘 보존되어있는지 확인.

        // 주어진 바이트 버퍼에 "world" 문자열을 추가한다. -> 처음에 저장한 hello world
        buf.writeBytes("world".getBytes());
        assertEquals(sourceData, buf.toString(Charset.defaultCharset())); // 초기 데이터와 같은지 확인

        assertEquals(13, buf.capacity());
        // 주어진 바이트 버퍼에 남은 바이트 수가 2인지 확인한다. -> 13바이트 크기의 바이트 버퍼에 11바이트를 기록하였으므로 기록 가능한 남은 바이트 수는 2다.
        assertEquals(2, buf.writableBytes());
    }
}