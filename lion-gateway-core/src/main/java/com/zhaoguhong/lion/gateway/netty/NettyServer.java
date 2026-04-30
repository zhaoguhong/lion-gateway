package com.zhaoguhong.lion.gateway.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
public class NettyServer {

  private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

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
      log.info("Lion Gateway started on port 9999 with JDK 21 Virtual Threads support");
      channel.closeFuture().sync();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Netty server interrupted", e);
    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

}
