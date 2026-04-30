package com.zhaoguhong.lion.gateway.rule.predicate;

/**
 * Data predicate interface
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public interface Predicate {

  /**
   * Data predicate test
   *
   * @param requestData request data
   * @param targetData  target data to compare with
   * @return true if the test passes
   */
  boolean test(String requestData, String targetData);

  /**
   * Get the operator string
   *
   * @return operator
   */
  String operator();

}
