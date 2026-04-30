package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Starts with predicate - check if request data starts with target string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class StartsWithPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return false;
    }
    return requestData.startsWith(targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.STARTS_WITH.getOperator();
  }

}
