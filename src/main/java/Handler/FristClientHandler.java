package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;

public class FristClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            System.out.println("打印 client接受到的消息   " + byteBuf.toString(Charset.defaultCharset())+"$");
            ctx.writeAndFlush("写入client接受到的消息    " + byteBuf.toString(Charset.defaultCharset())+"$\n");
        }finally {
            byteBuf.release();
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String resp="  client ready  \n";
        ByteBuf byteBuf= Unpooled.wrappedBuffer(resp.getBytes());
        ctx.channel().writeAndFlush(byteBuf);
        System.out.println("首次链接完成");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
