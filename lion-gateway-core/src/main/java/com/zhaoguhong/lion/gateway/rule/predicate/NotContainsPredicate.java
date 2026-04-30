package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Not contains predicate - check if request data does not contain target string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class NotContainsPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return true;
    }
    return !requestData.contains(targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.NOT_CONTAINS.getOperator();
  }

}
