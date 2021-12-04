package com.zhaoguhong.lion.gateway.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
public class NettyServer {

  public void start() {
    int availableProcessors = Runtime.getRuntime().availableProcessors();
    EventLoopGroup bossGroup = new NioEventLoopGroup(1,
        new DefaultThreadFactory("netty-boss", true));
    EventLoopGroup workerGroup = new NioEventLoopGroup(availableProcessors + 1,
        new DefaultThreadFactory("netty-worker", true));

    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new LionGatewayChannelInitializer());

    try {
      Channel channel = bootstrap.bind(9999).sync().channel();
      channel.closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

}
