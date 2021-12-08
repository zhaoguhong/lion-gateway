package com.zhaoguhong.lion.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 条件配置
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class ConditionConfig {

  /**
   * 请求数据类型
   */
  private String requestDataType;

  /**
   * 请求数据名称
   */
  private String requestDataName;

  /**
   * 操作符
   */
  private String operator;

  /**
   * 需要比对的目标数据
   */
  private String targetData;

  /**
   * 构造方法，解析配置的数据，配置用竖线分隔，比如 header,aaa|=|bbb
   *
   * @param condition 配置的数据
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
