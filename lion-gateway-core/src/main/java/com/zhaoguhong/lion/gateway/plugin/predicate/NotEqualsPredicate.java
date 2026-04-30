package com.zhaoguhong.lion.gateway.plugin.predicate;

import com.zhaoguhong.lion.gateway.common.enums.PredicateEnum;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * 不相等断言
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Component
public class NotEqualsPredicate implements Predicate {

  @Override
  public boolean test(String requestData, String targetData) {
    return !Objects.equals(requestData, targetData);
  }

  @Override
  public String operator() {
    return PredicateEnum.NOT_EQUALS.getOperator();
  }

}
