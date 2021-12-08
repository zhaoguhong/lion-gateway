package com.zhaoguhong.lion.gateway.config;

import com.zhaoguhong.lion.gateway.plugin.handler.DefaultHandlerChain;
import com.zhaoguhong.lion.gateway.plugin.handler.PluginHandler;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoguhong
 * @date 2021/11/26
 */
@Configuration
public class HandlerChainConfig {

  @Bean
  public DefaultHandlerChain DefaultHandlerChain(List<PluginHandler> pluginHandlers) {
    return new DefaultHandlerChain(pluginHandlers);
  }

}
