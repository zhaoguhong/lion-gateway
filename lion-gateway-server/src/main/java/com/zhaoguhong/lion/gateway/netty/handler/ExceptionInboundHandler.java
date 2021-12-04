package com.zhaoguhong.lion.gateway.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Slf4j
@ChannelHandler.Sharable
public class ExceptionInboundHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    log.error("InboundHandler exception", cause);
    ctx.flush();
    ctx.close();
  }

}
