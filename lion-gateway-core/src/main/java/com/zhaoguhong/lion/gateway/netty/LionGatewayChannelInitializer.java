package com.zhaoguhong.lion.gateway.netty;

import com.zhaoguhong.lion.gateway.netty.handler.ExceptionInboundHandler;
import com.zhaoguhong.lion.gateway.netty.handler.HttpRequestHandler;
import com.zhaoguhong.lion.gateway.plugin.handler.HandlerChain;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerKeepAliveHandler;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Component
public class LionGatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

  private static final int MAX_CONTENT_LENGTH = 1024 * 1024;

  private final HandlerChain handlerChain;

  CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin()
      .allowCredentials().build();

  @Autowired
  public LionGatewayChannelInitializer(HandlerChain handlerChain) {
    this.handlerChain = handlerChain;
  }

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {

    socketChannel.pipeline()
        .addLast("httpCodec", new HttpServerCodec())
        .addLast("httpKeepAlive", new HttpServerKeepAliveHandler())
        .addLast("httpAggregator", new HttpObjectAggregator(MAX_CONTENT_LENGTH))
        .addLast(new CorsHandler(corsConfig))
        .addLast(new HttpRequestHandler(handlerChain))
        .addLast(new ExceptionInboundHandler());

  }
}
