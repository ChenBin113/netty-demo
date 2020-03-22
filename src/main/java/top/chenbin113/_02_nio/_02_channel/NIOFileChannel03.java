package top.chenbin113._02_nio._02_channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 拷贝文件的内容
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("F:\\Code\\Java\\_05_deep\\netty-demo\\file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("F:\\Code\\Java\\_05_deep\\netty-demo\\file01_copy.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            // do not forget this line
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();

    }
}
