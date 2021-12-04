package com.zhaoguhong.lion.gateway.core.predicate;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 断言工厂类
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class PredicateFactory {

  private static Map<String, Predicate> predicates = Maps.newHashMap();

  public static Predicate get(String operator) {
    return predicates.get(operator);
  }

  @Autowired
  public void init(List<Predicate> predicateList) {
    predicateList
        .forEach(predicate -> predicates.put(predicate.operator(), predicate));
  }

}
