package com.zhaoguhong.lion.gateway.common.enums;

/**
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum PredicateEnum {

  /**
   * 相等
   */
  EQUALS("="),

  /**
   * 不相等
   */
  NOT_EQUALS("!=");

  public String getOperator() {
    return operator;
  }

  PredicateEnum(String operator) {
    this.operator = operator;
  }

  private String operator;

}
