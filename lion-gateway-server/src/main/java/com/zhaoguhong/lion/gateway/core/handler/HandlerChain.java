package com.zhaoguhong.lion.gateway.core.handler;

import com.zhaoguhong.lion.gateway.core.RequestContext;

/**
 * 处理器链
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public interface HandlerChain {

  /**
   * 处理请求
   *
   * @param requestContext 请求上下文
   */
  void handler(RequestContext requestContext);

}
