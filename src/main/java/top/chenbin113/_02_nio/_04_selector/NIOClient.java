package top.chenbin113._02_nio._04_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        // 提供服务器的 ip 和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                //System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作······");
            }
        }

        // connect success, send data
        String s = "Hello, world";

        // 可以根据发送的数据大小确定一个 ByteBuffer 的大小
        ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());

        // 发送数据，将 Buffer 写入 Channel
        socketChannel.write(byteBuffer);

        System.in.read();
    }
}
