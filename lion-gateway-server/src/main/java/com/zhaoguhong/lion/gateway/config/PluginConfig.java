package com.zhaoguhong.lion.gateway.config;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 插件配置
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PluginConfig {

  /**
   * 插件名称
   */
  private String name;

  /**
   * 开关，默认开启
   */
  private boolean enabled = true;

  /**
   * 规则配置
   */
  private List<RuleConfig> rules;

  /**
   * 扩展配置
   */
  private Map<String, Object> extend;

}
