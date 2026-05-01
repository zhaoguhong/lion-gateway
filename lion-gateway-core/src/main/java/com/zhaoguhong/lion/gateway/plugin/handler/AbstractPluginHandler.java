package com.zhaoguhong.lion.gateway.plugin.handler;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.config.PluginConfig;
import com.zhaoguhong.lion.gateway.config.PluginConfigManager;
import com.zhaoguhong.lion.gateway.config.RuleConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.rule.match.MatchMode;
import com.zhaoguhong.lion.gateway.rule.match.MatchModeFactory;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Base class for plugin handlers
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public abstract class AbstractPluginHandler implements PluginHandler {

  @Override
  public void handler(RequestContext requestContext, HandlerChain handlerChain) {
    PluginConfig pluginConfig = PluginConfigManager.getPlugin(name());

    // If current plugin is enabled and has rules configured
    if (pluginConfig != null && pluginConfig.isEnabled() && CollectionUtils
        .isNotEmpty(pluginConfig.getRules())) {

      for (RuleConfig ruleConfig : pluginConfig.getRules()) {
        List<ConditionConfig> conditionConfigs = ruleConfig.getConditions();
        if (CollectionUtils.isNotEmpty(conditionConfigs)) {
          // Match mode, for multiple conditions configuration, default is and match
          MatchMode matchMode = MatchModeFactory.get(ruleConfig.getMatchMode());
          // If rules match, execute custom plugin logic, waf plugin just returns error directly
          if (matchMode.match(requestContext, conditionConfigs)) {
            doHandler(requestContext, handlerChain);
            return;
          }
        }
      }
    }
    // No match, proceed to next handler
    handlerChain.handler(requestContext);
  }

  /**
   * Plugin processing logic, to be implemented by subclasses
   *
   * @param requestContext request context
   * @param handlerChain   handler chain
   */
  protected abstract void doHandler(RequestContext requestContext, HandlerChain handlerChain);
}
