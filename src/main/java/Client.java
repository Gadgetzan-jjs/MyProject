import Handler.FristClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client{
    private int port;
    private String host="127.0.0.1";
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public Client(int port){
        this.port=port;
    }
    EventLoopGroup clientworker = new NioEventLoopGroup();
    public void run(){
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(clientworker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new FristClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            if (channelFuture.isSuccess()){
                System.out.println("client 已启动");
            }
            this.channel=channelFuture.channel();
            channelFuture.channel().closeFuture().sync();
            System.out.println("client 即将关闭");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            clientworker.shutdownGracefully();
            System.out.println("client 已关闭");
        }


    }
}
