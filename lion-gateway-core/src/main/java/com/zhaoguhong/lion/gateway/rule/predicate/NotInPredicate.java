package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/**
 * Not in list predicate - check if request data is not in the comma-separated target list
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class NotInPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return true;
    }
    return !Arrays.asList(targetData.split(",")).contains(requestData);
  }

  @Override
  public String operator() {
    return PredicateEnum.NOT_IN.getOperator();
  }

}
