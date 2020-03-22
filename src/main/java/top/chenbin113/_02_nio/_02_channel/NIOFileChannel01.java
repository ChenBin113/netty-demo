package top.chenbin113._02_nio._02_channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 往本地文件写入数据
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String s = "Hello, world!";

        // 1. create FileOutputStream
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\Code\\Java\\_05_deep\\netty-demo\\file01.txt");

        // 2. get FileChannel
        FileChannel channel = fileOutputStream.getChannel();

        // 3. create ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 4. String -> ByteBuffer
        byteBuffer.get(s.getBytes());

        // 5. switch write status
        byteBuffer.flip();

        // 6. ByteBuffer -> FileChannel
        channel.write(byteBuffer);

        fileOutputStream.close();

    }
}
