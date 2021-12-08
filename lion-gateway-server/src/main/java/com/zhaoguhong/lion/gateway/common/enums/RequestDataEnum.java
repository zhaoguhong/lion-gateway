package com.zhaoguhong.lion.gateway.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 请求数据类型枚举类
 *
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum RequestDataEnum {

  /**
   * 请求头
   */
  HEADER("header");

  RequestDataEnum(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  private String type;


  private static Set<String> types;

  static {
    types = Collections.unmodifiableSet(
        Arrays.stream(RequestDataEnum.values()).map(RequestDataEnum::getType)
            .collect(Collectors.toSet())
    );
  }

  /**
   * 获取所有支持的请求数据类型
   *
   * @return
   */
  public static Set<String> types() {
    return types;
  }

  /**
   * 校验 type 是否有效
   *
   * @param type 插件名称
   * @return 是否有效
   */
  public static boolean validType(String type) {
    return types.contains(type);
  }

}
