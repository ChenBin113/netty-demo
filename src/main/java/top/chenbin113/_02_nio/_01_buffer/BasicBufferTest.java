package top.chenbin113._02_nio._01_buffer;

import java.nio.IntBuffer;

/**
 * how to use buffer
 */
public class BasicBufferTest {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(3);

        // save data in buffer
        for (int i = 0; i < intBuffer.capacity(); i++) {
            System.out.println("mark====" + intBuffer.mark());
            System.out.println("position====" + intBuffer.position());
            System.out.println("limit====" + intBuffer.limit());
            System.out.println("capacity====" + intBuffer.capacity());
            System.out.println("-----------------           begin             -------------------");

            intBuffer.put(i * 2);

            System.out.println("mark====" + intBuffer.mark());
            System.out.println("position====" + intBuffer.position());
            System.out.println("limit====" + intBuffer.limit());
            System.out.println("capacity====" + intBuffer.capacity());
            System.out.println("*******************          end               *****************");
        }

        // write status switch to read status
        intBuffer.flip();
        System.out.println(intBuffer.hasArray());

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }


    }
}
