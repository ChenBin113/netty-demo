package top.chenbin113._02_nio._02_channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 使用 transferFrom 方法拷贝文件
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\Code\\Java\\_05_deep\\netty-demo\\ironman.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\Code\\Java\\_05_deep\\netty-demo\\ironman_copy.jpg");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        inputStreamChannel.close();
        outputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
