package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * Regex match predicate - check if request data matches the target regular expression
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class RegexPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    if (requestData == null || targetData == null) {
      return false;
    }
    try {
      return Pattern.matches(targetData, requestData);
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String operator() {
    return PredicateEnum.REGEX.getOperator();
  }

}
