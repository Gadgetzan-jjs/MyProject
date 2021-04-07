package Handler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.internal.tcnative.Buffer;
import io.netty.util.CharsetUtil;
public class FirstServerHandler extends ChannelInboundHandlerAdapter {//添加逻辑代码处理接收到的msg


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        try {
            System.out.println("打印  server接受到的消息    " + buffer.toString(CharsetUtil.UTF_8)+"$");
            ByteBuf res = Unpooled.wrappedBuffer((new String("HTTP/1.1 200 OK\n"+"Sat, 31 Dec 2005 23:59:59 GMT\n"+"Connection:Keep-Alive\n"+"Content-Type:text/html:charset=UTF-8\n"+"Content-Length:117\n"+"\n"+"hello").getBytes()));
            ctx.writeAndFlush(res);
        }finally {
            buffer.release();
        }
    }
/*

链接成功之后自动启动该方法

 */

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("连接成功 this is server \n");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        /**
         * 异常捕获
         */
        cause.printStackTrace();
        ctx.close();
    }
}
