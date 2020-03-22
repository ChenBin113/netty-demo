package top.chenbin113._02_nio._03_detail;

import java.nio.ByteBuffer;

/**
 * 转换 Buffer 为只读
 * 实现类为 HeapByteBuffer
 */
public class ReadOnlyBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            buffer.put((byte)i);
        }

        buffer.flip();

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte)1); // Exception in thread "main" java.nio.ReadOnlyBufferException
    }
}
