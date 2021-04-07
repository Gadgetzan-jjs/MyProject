//package Handler;
//
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.handler.codec.http.DefaultFullHttpResponse;
//import io.netty.handler.codec.http.FullHttpResponse;
//import io.netty.handler.codec.http.HttpResponseStatus;
//import io.netty.handler.codec.http.HttpVersion;
//import io.netty.util.concurrent.EventExecutor;
//import io.netty.util.concurrent.EventExecutorGroup;
//import io.netty.util.concurrent.Future;
//import io.netty.util.concurrent.ScheduledFuture;
//
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//public class HttpServerHandler extends ChannelInboundHandlerAdapter {
//
//   @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws ExecutionException{
//       FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("ok".getBytes()));
//       response.headers().set()
//
//
//
//
//
//   }
//}
