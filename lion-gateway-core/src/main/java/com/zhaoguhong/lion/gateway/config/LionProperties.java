package com.zhaoguhong.lion.gateway.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目配置
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Configuration
@ConfigurationProperties("lion")
@Data
public class LionProperties {

  private Map<String, PluginConfig> plugins;

}
