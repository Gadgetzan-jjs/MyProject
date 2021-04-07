package Handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URL;
import java.net.URLConnection;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderNames.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class MyHttpHandler extends ChannelInboundHandlerAdapter {
    private FullHttpRequest request;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        FullHttpRequest fhr=(FullHttpRequest)msg;
//        ByteBuf msgcontent= fhr.content();
//        String stringmsg=msgcontent.toString(io.netty.util.CharsetUtil.UTF_8);
//        msgcontent.release();
//        String headcontent=fhr.uri();
//        System.out.println("报文头:"+"\n"+stringmsg+"\n"+"报文体"+"\n"+headcontent+"\n");
//        ctx.writeAndFlush("报文头:"+"\n"+stringmsg+"\n"+"报文体"+"\n"+headcontent+"\n");
//    }
        if (msg instanceof HttpRequest) {
            //这里可以去取header之类的东西
            FullHttpRequest request = (FullHttpRequest) msg;
            System.out.println("Uri:" + request.getUri());
//            System.out.println("Body:" + new String(request.content().array()));
        }
        if (msg instanceof HttpContent) {
            //这里来做content的相关处理吧
            try {
                HttpContent content = (HttpContent) msg;
                ByteBuf buf = content.content();
                String inputMessage = buf.toString(CharsetUtil.UTF_8);
                System.out.println("content:" + inputMessage);
////                buf.release();
//                InputJsonMessage jsonMessage = JSON.parseObject(inputMessage, InputJsonMessage.class);
//                returnMessage = InputProcessor.inputProcessor(jsonMessage.getHeader(), jsonMessage.getBody());
//                System.out.println(buf.toString(CharsetUtil.UTF_8));
//                if (!returnMessage.getIsSuccess()) {
//                    System.out.println(returnMessage.getMessage());
//                    return;
//                }
            } catch (Exception e) {
                System.out.println("bad bad bad");
            }
            String res = "aaaaaaa";
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(res.getBytes("UTF-8")));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            try {
                if (HttpUtil.isKeepAlive(request)) {
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                }
                ctx.write(response);
                System.out.println("-----" + 1);
                ctx.flush();
//

            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }


    }
}
