package com.zhaoguhong.lion.gateway.netty.handler;

import com.google.common.collect.Maps;
import com.zhaoguhong.lion.gateway.core.LionContext;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.plugin.handler.HandlerChain;
import com.zhaoguhong.lion.gateway.netty.util.NettyUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.util.CharsetUtil;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

  private static final String DEFAULT_ERROR_MESSAGE = "Internal Server Error";
  private static final String DEFAULT_RESPONSE = "response success";
  private static final int MAX_CONTENT_LENGTH = 1024 * 1024;

  static {
    DiskFileUpload.deleteOnExitTemporaryFile = false;
    DiskAttribute.deleteOnExitTemporaryFile = false;
  }

  private final HandlerChain handlerChain;

  public HttpRequestHandler(HandlerChain handlerChain) {
    this.handlerChain = handlerChain;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      FullHttpRequest request) throws Exception {

    log.info("request:{}", request);

    try {
      QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
      Map<String, String> queryParams = Maps.newHashMap();
      for (Map.Entry<String, List<String>> entry : decoder.parameters().entrySet()) {
        queryParams.put(entry.getKey(), entry.getValue().get(0));
      }

      if (request.method() == HttpMethod.GET) {
        log.info("method GET,params:{}", queryParams);
      } else if (request.method() == HttpMethod.POST) {
        if (NettyUtil.isHttpJsonRequest(request)) {
          String jsonParams = NettyUtil.getJsonParams(request);
          log.info("method POST,json params:{}", jsonParams);
        } else {
          Map<String, Object> postParams = NettyUtil.getPostParamsFromChannel(request);
          log.info("method POST,params:{}", postParams);
        }
      } else {
        writeErrorResponse(channelHandlerContext, HttpResponseStatus.METHOD_NOT_ALLOWED);
        return;
      }

      RequestContext requestContext = RequestContext.builder()
          .httpHeaders(request.headers())
          .path(decoder.path())
          .method(request.method().name())
          .queryParams(queryParams)
          .build();
      handlerChain.handler(requestContext);

      writeResponse(channelHandlerContext, requestContext.getResponse().toString());
    } catch (Exception e) {
      log.error("Error handling request", e);
      writeErrorResponse(channelHandlerContext, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void writeResponse(ChannelHandlerContext ctx, String content) {
    ByteBuf responseBuf = Unpooled.copiedBuffer(content, CharsetUtil.UTF_8);
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK, responseBuf);
    response.headers().set(HttpHeaderNames.CONTENT_TYPE,
        HttpHeaderValues.TEXT_PLAIN + "; charset=UTF-8");
    ctx.channel().writeAndFlush(response);
  }

  private void writeErrorResponse(ChannelHandlerContext ctx, HttpResponseStatus status) {
    ByteBuf responseBuf = Unpooled.copiedBuffer(DEFAULT_ERROR_MESSAGE, CharsetUtil.UTF_8);
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        status, responseBuf);
    response.headers().set(HttpHeaderNames.CONTENT_TYPE,
        HttpHeaderValues.TEXT_PLAIN + "; charset=UTF-8");
    ctx.channel().writeAndFlush(response);
  }
}
