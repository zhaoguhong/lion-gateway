package com.zhaoguhong.lion.gateway.plugin;

import com.zhaoguhong.lion.gateway.common.enums.PluginEnum;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.plugin.handler.AbstractPluginHandler;
import com.zhaoguhong.lion.gateway.plugin.handler.HandlerChain;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

/**
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class HttpPluginHandler extends AbstractPluginHandler {

  // 使用虚拟线程的 HttpClient
  private static final HttpClient httpClient = HttpClient.newBuilder()
      .executor(Executors.newVirtualThreadPerTaskExecutor()) // 🔥 虚拟线程！
      .build();

  @Override
  protected void doHandler(RequestContext requestContext, HandlerChain handlerChain) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("https://www.baidu.com/sugrec"))
          .GET()
          .build();

      // 在虚拟线程中，这样的阻塞调用不会有性能问题
      String response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
          .body();
      
      requestContext.setResponse(response);
    } catch (Exception e) {
      throw new RuntimeException("HTTP request failed", e);
    }
  }

  @Override
  public String name() {
    return PluginEnum.HTTP.getName();
  }

}
