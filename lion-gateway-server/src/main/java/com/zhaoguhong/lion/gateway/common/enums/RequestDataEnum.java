package com.zhaoguhong.lion.gateway.common.enums;

/**
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum RequestDataEnum {

  HEADER("header");

  public String getType() {
    return type;
  }

  RequestDataEnum(String type) {
    this.type = type;
  }

  private String type;

}
