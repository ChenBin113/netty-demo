package top.chenbin113._02_nio._02_channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 读取本地文件的数据
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        // 1. create FileInputStream
        File file = new File("F:\\Code\\Java\\_05_deep\\netty-demo\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        // 2. File's data -> FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();

        // 3. create ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 4. FileChannel -> ByteBuffer
        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();

    }
}
