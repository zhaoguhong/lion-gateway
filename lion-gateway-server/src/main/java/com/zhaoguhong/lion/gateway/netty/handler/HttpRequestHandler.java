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

  static {
    DiskFileUpload.deleteOnExitTemporaryFile = false;
    DiskAttribute.deleteOnExitTemporaryFile = false;
  }
  private HandlerChain handlerChain = LionContext.getBean(HandlerChain.class);

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext,
      FullHttpRequest request) throws Exception {

    log.info("request:{}", request);
    ByteBuf responseBuf = Unpooled.copiedBuffer("response success", CharsetUtil.UTF_8);
    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
        HttpResponseStatus.OK, responseBuf);
    if (request.method() == HttpMethod.GET) {
      log.info("method GET,params:{}", getUriParams(request));
    } else if (request.method() == HttpMethod.POST) {
      if (NettyUtil.isHttpJsonRequest(request)) {
        String jsonParams = NettyUtil.getJsonParams(request);
        log.info("method POST,json params:{}", jsonParams);
      } else {
        Map<String, Object> postParams = NettyUtil.getPostParamsFromChannel(request);
        log.info("method POST,params:{}", postParams);
      }
    } else {
      response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
          HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }

    RequestContext requestContext = RequestContext.builder().httpHeaders(request.headers()).build();
    handlerChain.handler(requestContext);


    // 响应数据
    ChannelFuture f = channelHandlerContext.channel().writeAndFlush(response);
  }

  /*
   * 获取url参数
   */
  private Map<String, Object> getUriParams(FullHttpRequest request) {
    Map<String, Object> params = Maps.newHashMap();
    if (request.method() == HttpMethod.GET) {
      QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
      for (Map.Entry<String, List<String>> entry : decoder.parameters().entrySet()) {
        params.put(entry.getKey(), entry.getValue().get(0));
      }
    }
    return params;
  }
}
