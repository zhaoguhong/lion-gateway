package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Contains predicate - check if request data contains target string
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class ContainsPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return false;
    }
    return requestData.contains(targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.CONTAINS.getOperator();
  }

}
