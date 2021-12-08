package com.zhaoguhong.lion.gateway.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 插件枚举类
 *
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum PluginEnum {

  /**
   * waf 插件
   */
  WAF("waf");

  PluginEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private String name;

  private static Set<String> names;

  static {
    names = Collections.unmodifiableSet(
        Arrays.stream(PluginEnum.values()).map(PluginEnum::getName)
            .collect(Collectors.toSet())
    );
  }

  /**
   * 获取所有的插件名称
   *
   * @return
   */
  public static Set<String> names() {
    return names;
  }

  /**
   * 校验 name 是否有效
   *
   * @param name 插件名称
   * @return 是否有效
   */
  public static boolean validName(String name) {
    return names.contains(name);
  }

}
