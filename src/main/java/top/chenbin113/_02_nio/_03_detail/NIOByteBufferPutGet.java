package top.chenbin113._02_nio._03_detail;

import java.nio.ByteBuffer;

/**
 * 写入和读取要同类型，否则容易出错误：java.nio.BufferUnderflowException
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);

        buffer.flip();

        // =============================================================
        // 打印注释中的代码会出现 Exception in thread "main" java.nio.BufferUnderflowException
        //System.out.println(buffer.get());
        //System.out.println(buffer.getInt());

        System.out.println(buffer.getInt()); // 100

    }
}
