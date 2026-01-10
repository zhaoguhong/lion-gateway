package com.zhaoguhong.lion.gateway.plugin.handler;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.config.PluginConfig;
import com.zhaoguhong.lion.gateway.config.PluginConfigManager;
import com.zhaoguhong.lion.gateway.config.RuleConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.plugin.match.MatchMode;
import com.zhaoguhong.lion.gateway.plugin.match.MatchModeFactory;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * 插件处理器基类
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public abstract class AbstractPluginHandler implements PluginHandler {

  @Override
  public void handler(RequestContext requestContext, HandlerChain handlerChain) {
    PluginConfig pluginConfig = PluginConfigManager.getPlugin(name());

    // 如果当前插件开启，并且配置了规则
    if (pluginConfig != null && pluginConfig.isEnabled() && CollectionUtils
        .isNotEmpty(pluginConfig.getRules())) {

      for (RuleConfig ruleConfig : pluginConfig.getRules()) {
        List<ConditionConfig> conditionConfigs = ruleConfig.getConditions();
        if (CollectionUtils.isNotEmpty(conditionConfigs)) {
          // 匹配模式，主要对于配置多个条件的情况，默认 and 匹配
          MatchMode matchMode = MatchModeFactory.get(ruleConfig.getMatchMode());
          // 如果满足规则，走自定义的插件逻辑，waf 插件其实就是直接返回错误
          if (matchMode.match(requestContext, conditionConfigs)) {
            doHandler(requestContext, handlerChain);
            return;
          }
        }
      }
    }
    // 没有匹配到，走下一个 handler
    handlerChain.handler(requestContext);
  }

  /**
   * 插件处理逻辑，留给子类去实现
   *
   * @param requestContext 请求上下文
   * @param handlerChain   处理器链
   */
  protected abstract void doHandler(RequestContext requestContext, HandlerChain handlerChain);
}
