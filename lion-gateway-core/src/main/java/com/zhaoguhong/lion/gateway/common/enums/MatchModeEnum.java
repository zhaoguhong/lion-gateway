package com.zhaoguhong.lion.gateway.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 匹配模式枚举类
 *
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


  MatchModeEnum(String mode) {
    this.mode = mode;
  }

  public String getMode() {
    return mode;
  }

  private String mode;

  private static Set<String> modes;

  static {
    modes = Collections.unmodifiableSet(
        Arrays.stream(MatchModeEnum.values()).map(MatchModeEnum::getMode)
            .collect(Collectors.toSet())
    );
  }

  /**
   * 获取所有的匹配模式
   *
   * @return
   */
  public static Set<String> modes() {
    return modes;
  }

  /**
   * 校验 mode 是否有效
   *
   * @param mode 匹配模式
   * @return
   */
  public static boolean validMode(String mode) {
    return modes.contains(mode);
  }

}
