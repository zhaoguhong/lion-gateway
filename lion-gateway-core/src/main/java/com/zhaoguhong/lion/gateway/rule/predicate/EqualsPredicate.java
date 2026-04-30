package com.zhaoguhong.lion.gateway.rule.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * Equals predicate - check if request data equals target data
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Component
public class EqualsPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    return Objects.equals(requestData, targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.EQUALS.getOperator();
  }

}
