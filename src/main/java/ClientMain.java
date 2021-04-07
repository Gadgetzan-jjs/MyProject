import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        final Client client=new Client(ServerMain.port);
        Thread thread=new Thread(new Runnable() {
            public void run() {
                client.run();
            }
        });
        thread.start();
        Channel channel = null;
        boolean isStart = false;
        while (!isStart) {
            if (null != client.getChannel()) {
                channel = client.getChannel();
                isStart = true;
            }
        }
//        String helo = "你好呀!我这里是客户端, 收到请回答";
//        ByteBuf byteBuf = Unpooled.wrappedBuffer(helo.getBytes());
//        channel.writeAndFlush(byteBuf);
        /**
         * 我们通过控制台输入来给服务端发送消息
         * 此处只做模拟使用
         */
        for (int i = 0; i < 10 ; i++) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            channel.writeAndFlush(Unpooled.wrappedBuffer(text.getBytes()));
        }
    }
}
