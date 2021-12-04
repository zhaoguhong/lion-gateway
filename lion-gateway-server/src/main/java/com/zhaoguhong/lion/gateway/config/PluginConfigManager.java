package com.zhaoguhong.lion.gateway.config;

import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 配置管理器
 *
 * @author zhaoguhong
 * @date 2021/11/29
 */
@Component
public class PluginConfigManager {

  private static Map<String, PluginConfig> plugins = Maps.newConcurrentMap();

  @Autowired
  public void initPlugin(LionProperties lionProperties) {
    lionProperties.getPlugins()
        .forEach((pluginName, pluginConfig) -> this.addPlugin(pluginName, pluginConfig));
  }

  public static PluginConfig getPlugin(String pluginName) {
    return plugins.get(pluginName);
  }

  public void removePlugin(String pluginName) {
    plugins.remove(pluginName);
  }

  public void addPlugin(String pluginName, PluginConfig pluginConfig) {
    pluginConfig.setName(pluginName);
    plugins.put(pluginName, pluginConfig);
  }

}
