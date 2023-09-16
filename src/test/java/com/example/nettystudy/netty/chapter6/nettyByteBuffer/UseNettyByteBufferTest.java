package com.example.nettystudy.netty.chapter6.nettyByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseNettyByteBufferTest {
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

        // 버퍼에 4바이트 크기의 정수 66537을 기록, 데이터를 기록하는 write 메서드는 기록한 데이터 크기만큼 writeIndex 속성값을 증가시킨다.
        buf.writeInt(66537);
        // 주어진 바이트 버퍼에서 읽어들일 수 있는 바이트 크기가 4인지 확인, 11바이트 크기의 바이트 버퍼에 4바이트를 기록했으므로 읽을 수 있는 바이트 수는 4다.
        assertEquals(4, buf.readableBytes());
        assertEquals(7, buf.writableBytes());

        // 주어진 바이트 버퍼에서 2바이트 크기의 정수를 읽고, 읽어들은 값이 1과 같은지 확인.
        // 66537을 16진수로 표현하면 0x10001 이고 이 값에 4바이트 패딩을 하면 0x00010001이 된다.
        // 그러므로 2바이트를 읽었을 때의 값은 1이다.
        assertEquals(1, buf.readShort());

        // 주어진 바이트 버퍼에서 읽을 수 있는 바이트 수가 2인지 확인한다.
        // 4바이트가 기록된 버퍼에서 2바이트 정수 데이터를 읽었으므로 남은 바이트 수는 2다.
        // 데이터를 읽어들이는 read 메서드는 읽어들인 데이터만큼 readIndex를 감소시킨다.
        assertEquals(2, buf.readableBytes());

        // 주어진 버퍼에 기록할 수 있는 바이트 수가 7인지 확인한다.
        // 네티의 바이트 버퍼는 읽기 인덱스와 쓰기 인덱스를 별도로 관리하므로 읽기 인덱스가 변경된다고 해서 쓰기 인덱스가 변경되지는 않는다.
        assertEquals(7, buf.writableBytes());


        // 주어진 바이트 버퍼에 아직 읽어들일 데이터가 남았는지 확인한다.
        // isReadable 메서드는 바이트 버퍼에 읽지 않은 데이터가 남았는지 확인하기 위해서 쓰기 인덱스가 읽기 인덱스보다 큰지 검사한다.
        assertTrue(buf.isReadable());

        // 주어진 바이트 버퍼를 초기화하는 clear -> 주어진 버퍼의 읽기 인덱스와 쓰기 인덱스 값을 모두 0으로 변경
        buf.clear();

        assertEquals(0, buf.readableBytes());
        assertEquals(11, buf.writableBytes());

    }

}