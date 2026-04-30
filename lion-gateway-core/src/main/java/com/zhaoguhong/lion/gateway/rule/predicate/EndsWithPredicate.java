package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Ends with predicate - check if request data ends with target string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class EndsWithPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return false;
    }
    return requestData.endsWith(targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.ENDS_WITH.getOperator();
  }

}
