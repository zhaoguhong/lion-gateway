package com.zhaoguhong.lion.gateway.common.enums;

/**
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum MatchModeEnum {

  /**
   * 全部满足
   */
  AND("and"),

  /**
   * 任何一个满足
   */
  OR("or"),

  /**
   * 没有一个满足
   */
  NONE("none");

  public String getMode() {
    return mode;
  }

  MatchModeEnum(String mode) {
    this.mode = mode;
  }

  private String mode;

}
