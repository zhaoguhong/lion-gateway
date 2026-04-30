package com.zhaoguhong.lion.gateway.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 断言枚举类
 *
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

  private static Set<String> operators;

  static {
    operators = Collections.unmodifiableSet(
        Arrays.stream(PredicateEnum.values()).map(PredicateEnum::getOperator)
            .collect(Collectors.toSet())
    );
  }

  /**
   * 获取所有支持的操作符
   *
   * @return
   */
  public static Set<String> operators() {
    return operators;
  }

  /**
   * 校验 operator 是否有效
   *
   * @param operator 操作符
   * @return 是否有效
   */
  public static boolean validOperator(String operator) {
    return operators.contains(operator);
  }

}
