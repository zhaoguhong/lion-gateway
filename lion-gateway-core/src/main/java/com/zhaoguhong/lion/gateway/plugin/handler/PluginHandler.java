package com.zhaoguhong.lion.gateway.plugin.handler;

import com.zhaoguhong.lion.gateway.core.RequestContext;

/**
 * 插件处理器
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public interface PluginHandler {

  /**
   * 处理请求
   *
   * @param requestContext 请求上下文
   * @param handlerChain   处理器链
   */
  void handler(RequestContext requestContext, HandlerChain handlerChain);

  /**
   * 插件名称
   *
   * @return
   */
  String name();
}
