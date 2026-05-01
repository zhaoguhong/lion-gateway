package com.zhaoguhong.lion.gateway.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Condition configuration
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@ToString(exclude = {"rule"})
public class ConditionConfig {

  /**
   * Request data type
   */
  private String requestDataType;

  /**
   * Request data name
   */
  private String requestDataName;

  /**
   * Operator
   */
  private String operator;

  /**
   * Target data to compare
   */
  private String targetData;

  /**
   * rule config, reverse reference
   */
  @JsonIgnore
  private RuleConfig rule;

  /**
   * Constructor, parse configuration data separated by vertical lines, e.g. header,aaa|=|bbb
   *
   * @param condition configuration data
   */
  public ConditionConfig(String condition) {
    String[] conditionArray = condition.split("\\|");
    if (conditionArray.length != 3) {
      log.error("condition format error,condition[{}]", condition);
      return;
    }
    String requestData = conditionArray[0];
    this.operator = conditionArray[1];
    this.targetData = conditionArray[2];

    String[] requestDataArray = requestData.split(",");
    this.requestDataType = requestDataArray[0];
    if (requestDataArray.length > 1) {
      this.requestDataName = requestDataArray[1];
    }
  }

}
