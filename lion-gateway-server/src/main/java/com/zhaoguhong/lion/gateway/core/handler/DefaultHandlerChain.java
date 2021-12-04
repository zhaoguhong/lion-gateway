package com.zhaoguhong.lion.gateway.core.handler;

import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.List;
import org.springframework.util.Assert;

/**
 * 默认的处理器链
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public class DefaultHandlerChain implements HandlerChain {

  private List<PluginHandler> pluginHandlers;

  public DefaultHandlerChain(List<PluginHandler> pluginHandlers) {
    Assert.notNull(pluginHandlers, "pluginHandlers cannot be null");
    this.pluginHandlers = pluginHandlers;
  }

  @Override
  public void handler(RequestContext requestContext) {
    // 每次创建一个新对象，保证线程安全
    VirtualHandlerChain virtualHandlerChain = new VirtualHandlerChain(pluginHandlers);
    virtualHandlerChain.handler(requestContext);
  }

  private class VirtualHandlerChain implements HandlerChain {

    private int currentPosition = 0;

    private final List<PluginHandler> pluginHandlers;

    public VirtualHandlerChain(List<PluginHandler> pluginHandlers) {
      this.pluginHandlers = pluginHandlers;
    }

    @Override
    public void handler(RequestContext requestContext) {
      if (currentPosition < pluginHandlers.size()) {
        pluginHandlers.get(currentPosition++).handler(requestContext, this);
      }
    }
  }

}
