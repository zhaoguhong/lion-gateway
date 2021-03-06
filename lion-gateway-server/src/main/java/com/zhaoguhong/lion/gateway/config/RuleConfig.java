package com.zhaoguhong.lion.gateway.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 规则配置
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleConfig {

  /**
   * 规则名称
   */
  private String name;

  /**
   * 规则匹配模式
   */
  private String matchMode;

  /**
   * 规则匹配条件配置
   */
  private List<ConditionConfig> conditions;

}
