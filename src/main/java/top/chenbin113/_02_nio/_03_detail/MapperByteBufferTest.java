package top.chenbin113._02_nio._03_detail;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要拷贝一次
 * 操作系统级别的修改，性能比较高
 * 实现类：DirectByteBuffer
 */
public class MapperByteBufferTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("file01.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        /*
         * 参数 1：FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数 2：0 可以直接修改的起始位置
         * 参数 3：5 是映射到内存的大小(不是索引位置) ,即将 file01.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte)'A');

        randomAccessFile.close();

        System.out.println("修改成功");

    }
}
