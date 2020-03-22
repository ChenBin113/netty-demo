package top.chenbin113._02_nio._04_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建 ServerSocketChannel -> SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 获取一个选择器
        Selector selector = Selector.open();

        // 绑定一个端口，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // serverSocketChannel 注册到 selector 中，事件为 OP_ACCEPT，即客户端连接服务器端的请求
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的 SelectionKey 数量 = " + selector.keys().size());

        // 等待客户端连接
        while (true) {

            // 等待 1s，如果没有事件发生，则跳过此次循环
            if (selector.select(1000) == 0) {
                // System.out.println("服务器等待了 1s ，无连接");
                continue;
            }

            // 此处说明有时间发生，获得 Set<SelectionKey>
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectionKeys 的数量：" + selectionKeys.size());

            // 使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {

                // 获取每一个事件的 SelectionKey
                SelectionKey selectionKey = keyIterator.next();

                // 根据 key 对应的事件进行处理
                if (selectionKey.isAcceptable()) {

                    // 在客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    System.out.println("客户端连接成功，生成了 SocketChannel " + socketChannel.hashCode());

                    // socketChannel 设置为非阻塞
                    socketChannel.configureBlocking(false);

                    // socketChannel 注册到 selector，关注事件 OP_READ，给 socketChannel 关联一个 Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("selectionKey.isAcceptable()：" + selectionKeys.size());
                }

                // 发生 OP_READ
                if (selectionKey.isReadable()) {

                    // 获得 SocketChannel
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    // 获得 ByteBuffer
                    ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();

                    socketChannel.read(byteBuffer);

                    System.out.println("from 客户端 " + new String(byteBuffer.array()));

                    System.out.println("selectionKey.isReadable()：" + selectionKeys.size());
                }

                // 完成事件对应的操作后，移除事件对应的 key
                keyIterator.remove();

                System.out.println("keyIterator.remove()：" + selectionKeys.size());
            }
        }

    }
}


