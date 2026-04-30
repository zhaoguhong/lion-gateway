package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Empty predicate - check if request data is null or empty string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class EmptyPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    return requestData == null || requestData.isEmpty();
  }

  @Override
  public String operator() {
    return PredicateEnum.EMPTY.getOperator();
  }

}
