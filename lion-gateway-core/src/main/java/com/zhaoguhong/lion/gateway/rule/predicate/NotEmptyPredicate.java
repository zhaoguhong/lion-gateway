package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Not empty predicate - check if request data is not null and not empty string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class NotEmptyPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    return requestData != null && !requestData.isEmpty();
  }

  @Override
  public String operator() {
    return PredicateEnum.NOT_EMPTY.getOperator();
  }

}
