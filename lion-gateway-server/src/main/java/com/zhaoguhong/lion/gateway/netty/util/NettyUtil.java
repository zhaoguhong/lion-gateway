package com.zhaoguhong.lion.gateway.netty.util;

import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED;
import static io.netty.handler.codec.http.HttpHeaderValues.MULTIPART_FORM_DATA;
import static io.netty.handler.codec.http.HttpHeaderValues.MULTIPART_MIXED;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 工具类
 *
 * @author guhong
 * @date 2021/2/17
 */
@Slf4j
public class NettyUtil {

  /**
   * 判断参数是否是json的http请求
   *
   * @return
   */
  public static boolean isHttpJsonRequest(FullHttpRequest request) {
    HttpHeaders headers = request.headers();
    String contentType = headers.get(HttpHeaderNames.CONTENT_TYPE);
    return contentType.startsWith(APPLICATION_JSON.toString());
  }

  /**
   * 获取 json 参数
   */
  public static String getJsonParams(FullHttpRequest request) {
    ByteBuf content = request.content();
    byte[] reqContent = new byte[content.readableBytes()];
    content.readBytes(reqContent);
    String strContent = new String(reqContent, StandardCharsets.UTF_8);
    return strContent;
  }

  /**
   * 获取POST方式传递的参数
   */
  public static Map<String, Object> getPostParamsFromChannel(FullHttpRequest request) {
    HttpHeaders headers = request.headers();
    Map<String, Object> params = new HashMap<String, Object>();
    String contentType = headers.get(HttpHeaderNames.CONTENT_TYPE);

    if (request.method() == HttpMethod.POST) {
      if (contentType.startsWith(MULTIPART_FORM_DATA.toString()) ||
          contentType.startsWith(APPLICATION_X_WWW_FORM_URLENCODED.toString()) ||
          contentType.startsWith(MULTIPART_MIXED.toString())) {
        params = getFormParams(request);
      } else {
        log.warn("url:{}, content-type:{} not support", request.uri(), contentType);
      }
    }
    return params;
  }

  /*
   * 获取body 里面参数
   */
  public static Map<String, Object> getFormParams(FullHttpRequest request) {
    Map<String, Object> params = Maps.newHashMap();
    HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
    List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
    try {
      for (InterfaceHttpData data : postData) {
        HttpDataType dataType = data.getHttpDataType();
        if (dataType == InterfaceHttpData.HttpDataType.Attribute) {
          Attribute attribute = (Attribute) data;
          params.put(attribute.getName(), attribute.getValue());
        } else if (dataType.equals(HttpDataType.FileUpload)) {
          log.warn("url:{}, HttpDataType.FileUpload not support", request.uri());
        } else if (dataType.equals(HttpDataType.InternalAttribute)) {
          log.warn("url:{}, HttpDataType.InternalAttribute not support", request.uri());
        }
      }
      return params;
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      decoder.destroy();
    }
  }

  public static Map<String, Object> getFormParams1(FullHttpRequest request) {
    Map<String, Object> params = Maps.newHashMap();
    // 使用 netty 提供的解码器解析 post 请求
    HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
    try {
      for (InterfaceHttpData data : decoder.getBodyHttpDatas()) {
        // TODO 根据自己的需求处理 body 数据
      }
      return params;
    } finally {
      decoder.destroy();
    }
  }

}
