package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import org.springframework.stereotype.Component;

/**
 * Greater than or equal predicate - numeric comparison, check if request value is >= target
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class GreaterThanOrEqualPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return false;
    }
    try {
      double requestNum = Double.parseDouble(requestData);
      double targetNum = Double.parseDouble(targetData);
      return requestNum >= targetNum;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public String operator() {
    return PredicateEnum.GE.getOperator();
  }

}
