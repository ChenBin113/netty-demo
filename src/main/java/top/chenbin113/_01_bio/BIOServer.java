package top.chenbin113._01_bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * client using `telnet 127.0.0.1 6666` connect server
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // using pool to get thread
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // create socket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动");

        while (true) {
            System.out.println("线程 id：" + Thread.currentThread().getId() + " 名字：" + Thread.currentThread().getName());
            System.out.println("等待连接······");

            // client socket
            final Socket socket = serverSocket.accept();

            System.out.println("连接到一个客户端");

            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }

    }

    private static void handler(Socket socket) {
        try {
            System.out.println("线程 id：" + Thread.currentThread().getId() + " 名字：" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                System.out.println("线程 id：" + Thread.currentThread().getId() + " 名字：" + Thread.currentThread().getName());

                System.out.println("read······");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和 client 的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
