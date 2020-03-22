package top.chenbin113._02_nio._03_detail;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering：将数据写入到 buffer 时，可以采用 buffer 数组，依次写入，一个满了再写另一个 [分散]
 * Gathering：从 buffer 读取数据时，可以采用 buffer 数组，依次读
 * 这个案例说明，多个 Buffer 可以叠加使用的功能
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        // 等客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 假定从客户端接收 8 个字节，设置了两个 Buffer 容量为 8
        int messageLength = 8;

        while (true) {
            long byteRead = 0;

            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);

                // 累计读取的字节数
                byteRead += l;
                System.out.println("byteRead = " + byteRead);

                // 使用流打印，看看当前的这个 buffer 的 position 和 limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "position = " + buffer.position() + ", limit = " + buffer.limit()).forEach(System.out::println);
            }

            // 将所有的 Buffer 进行 flip()
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }


            // 将所有的 Buffer 进行 clear()
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());

            System.out.println("byteRead = " + byteRead + " byteWrite = " + byteWrite + " messageLength = " + messageLength);
        }
    }
}
