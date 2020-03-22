package top.chenbin113._02_nio._05_groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务器端：可以监测用户上线，离线，并实现消息转发功能
 */
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            // 准备好 ServerSocketChannel 和 Selector
            listenChannel = ServerSocketChannel.open();
            selector = Selector.open();

            // 绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));

            // 设置非阻塞
            listenChannel.configureBlocking(false);

            // 注册进 Selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);

                if (count > 0) { // 有事件需要处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线 ");
                        }

                        if (key.isReadable()) {

                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待···········");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
