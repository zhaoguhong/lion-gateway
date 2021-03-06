package com.zhaoguhong.lion.gateway.config;

import com.google.common.collect.Maps;
import com.zhaoguhong.lion.gateway.common.enums.MatchModeEnum;
import com.zhaoguhong.lion.gateway.common.enums.PluginEnum;
import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import com.zhaoguhong.lion.gateway.common.enums.RequestDataEnum;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 配置管理器
 *
 * @author zhaoguhong
 * @date 2021/11/29
 */
@Component
@Slf4j
public class PluginConfigManager {

  private static Map<String, PluginConfig> plugins = Maps.newConcurrentMap();

  @Autowired
  public void initPlugin(LionProperties lionProperties) {
    lionProperties.getPlugins()
        .forEach((pluginName, pluginConfig) -> addPlugin(pluginName, pluginConfig));
  }

  public static PluginConfig getPlugin(String pluginName) {
    return plugins.get(pluginName);
  }

  public static void removePlugin(String pluginName) {
    plugins.remove(pluginName);
  }

  public static boolean addPlugin(String pluginName, PluginConfig pluginConfig) {
    pluginConfig.setName(pluginName);
    if (!checkPlugin(pluginConfig)) {
      log.error("plugin add fail, pluginConfig: {}", pluginConfig);
      return false;
    }
    plugins.put(pluginName, pluginConfig);
    return true;
  }

  /**
   * check plugin config
   *
   * @param pluginConfig
   * @return
   */
  public static boolean checkPlugin(PluginConfig pluginConfig) {
    if (!PluginEnum.validName(pluginConfig.getName())) {
      log.error("plugin name[{}] is invalid, only support:{}", pluginConfig.getName(),
          PluginEnum.names());
      return false;
    }
    if (pluginConfig.getRules() != null) {
      return pluginConfig.getRules().stream().allMatch(rule -> checkRuleConfig(rule));
    }
    return true;
  }

  /**
   * check rule config
   *
   * @param rule
   * @return
   */
  public static boolean checkRuleConfig(RuleConfig rule) {
    if (!MatchModeEnum.validMode(rule.getMatchMode())) {
      log.error("rule name[{}], matchMode[{}] is invalid, only support:{}", rule.getName(),
          rule.getMatchMode(),
          MatchModeEnum.modes());
      return false;
    }
    if (rule.getConditions() != null) {
      // set reverse reference
      rule.getConditions().forEach(condition -> condition.setRule(rule));
      return rule.getConditions().stream().allMatch(condition -> checkConditionConfig(condition));
    }
    return true;
  }

  /**
   * check condition config
   *
   * @param condition
   * @return
   */
  public static boolean checkConditionConfig(ConditionConfig condition) {
    RuleConfig rule = condition.getRule();
    if (StringUtils.isBlank(condition.getTargetData())) {
      log.error("rule name[{}], targetData is required", rule.getName());
      return false;
    }
    if (!RequestDataEnum.validType(condition.getRequestDataType())) {
      log.error("rule name[{}], requestDataType[{}] is invalid, only support:{}",
          rule.getName(),
          condition.getRequestDataType(),
          RequestDataEnum.types());
      return false;
    }
    if (!PredicateEnum.validOperator(condition.getOperator())) {
      log.error("rule name[{}], operator[{}] is invalid, only support:{}",
          rule.getName(),
          condition.getOperator(),
          PredicateEnum.operators());
      return false;
    }
    return true;
  }

}
