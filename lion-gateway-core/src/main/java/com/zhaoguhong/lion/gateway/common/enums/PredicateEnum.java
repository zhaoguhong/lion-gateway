package com.zhaoguhong.lion.gateway.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Predicate enum - defines all supported operators
 *
 * @author zhaoguhong
 * @date 2021/12/2
 */
public enum PredicateEnum {

  /**
   * equals
   */
  EQUALS("="),

  /**
   * not equals
   */
  NOT_EQUALS("!="),

  /**
   * contains
   */
  CONTAINS("contains"),

  /**
   * not contains
   */
  NOT_CONTAINS("not_contains"),

  /**
   * starts with
   */
  STARTS_WITH("starts_with"),

  /**
   * ends with
   */
  ENDS_WITH("ends_with"),

  /**
   * regex match
   */
  REGEX("regex"),

  /**
   * is empty or null
   */
  EMPTY("empty"),

  /**
   * is not empty
   */
  NOT_EMPTY("not_empty"),

  /**
   * in list (comma separated)
   */
  IN("in"),

  /**
   * not in list
   */
  NOT_IN("not_in"),

  /**
   * greater than (numeric comparison)
   */
  GT(">"),

  /**
   * less than (numeric comparison)
   */
  LT("<"),

  /**
   * greater than or equal (numeric comparison)
   */
  GE(">="),

  /**
   * less than or equal (numeric comparison)
   */
  LE("<=");

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
   * Get all supported operators
   *
   * @return set of operator strings
   */
  public static Set<String> operators() {
    return operators;
  }

  /**
   * Validate if operator is supported
   *
   * @param operator operator string
   * @return true if operator is valid
   */
  public static boolean validOperator(String operator) {
    return operators.contains(operator);
  }

}
