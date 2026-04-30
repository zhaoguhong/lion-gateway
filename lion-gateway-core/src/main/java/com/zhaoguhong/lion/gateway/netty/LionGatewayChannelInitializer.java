package com.zhaoguhong.lion.gateway.netty;

import com.zhaoguhong.lion.gateway.netty.handler.ExceptionInboundHandler;
import com.zhaoguhong.lion.gateway.netty.handler.HttpRequestHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerKeepAliveHandler;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
public class LionGatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

  CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin()
      .allowCredentials().build();

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {

    socketChannel.pipeline()
        .addLast("httpCodec", new HttpServerCodec())
        .addLast("httpKeepAlive", new HttpServerKeepAliveHandler())
        // http message Aggregator
        .addLast("httpAggregator", new HttpObjectAggregator(1024 * 1024))
        .addLast(new CorsHandler(corsConfig))
        .addLast(new HttpRequestHandler())
        // 统一的异常处理
        .addLast(new ExceptionInboundHandler());

  }
}
