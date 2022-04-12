package com.zhaoguhong.lion.gateway.plugin;

import com.zhaoguhong.lion.gateway.common.enums.PluginEnum;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.plugin.handler.AbstractPluginHandler;
import com.zhaoguhong.lion.gateway.plugin.handler.HandlerChain;
import org.springframework.stereotype.Component;
import reactor.netty.http.client.HttpClient;

/**
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class HttpPluginHandler extends AbstractPluginHandler {

  @Override
  protected void doHandler(RequestContext requestContext, HandlerChain handlerChain) {
    HttpClient client = HttpClient.create();

    String response = client.get()
        .uri("https://www.baidu.com/sugrec")
        .responseContent().aggregate()
        .asString().block();
    requestContext.setResponse(response);
  }

  @Override
  public String name() {
    return PluginEnum.HTTP.getName();
  }

}
