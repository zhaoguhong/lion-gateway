package com.zhaoguhong.lion.gateway.plugin;

import com.zhaoguhong.lion.gateway.common.enums.PluginEnum;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.core.handler.AbstractPluginHandler;
import com.zhaoguhong.lion.gateway.core.handler.HandlerChain;
import org.springframework.stereotype.Component;

/**
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class WafPluginHandler extends AbstractPluginHandler {

  @Override
  protected void doHandler(RequestContext requestContext, HandlerChain handlerChain) {
    System.out.println("执行 waf 插件");
  }

  @Override
  public String name() {
    return PluginEnum.WAF.getName();
  }

}
