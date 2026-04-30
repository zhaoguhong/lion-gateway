package com.zhaoguhong.lion.gateway.rule.predicate;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Predicate factory - get predicate instance by operator
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class PredicateFactory {

  private static Map<String, Predicate> predicates = Maps.newHashMap();

  /**
   * Get predicate by operator
   *
   * @param operator operator string
   * @return predicate instance, or null if not found
   */
  public static Predicate get(String operator) {
    return predicates.get(operator);
  }

  /**
   * Initialize predicate map with all available predicates
   *
   * @param predicateList list of predicate beans from Spring context
   */
  @Autowired
  public void init(List<Predicate> predicateList) {
    predicateList
        .forEach(predicate -> predicates.put(predicate.operator(), predicate));
  }

}
