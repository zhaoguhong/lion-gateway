package com.zhaoguhong.lion.gateway.common.enums;

/**
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum PluginEnum {

  WAF("waf");

  public String getName() {
    return name;
  }

  PluginEnum(String name) {
    this.name = name;
  }

  private String name;

}
