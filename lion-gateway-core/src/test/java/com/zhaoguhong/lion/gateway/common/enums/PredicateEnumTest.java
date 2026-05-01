package com.zhaoguhong.lion.gateway.common.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PredicateEnumTest {

  @Test
  void testValidOperator() {
    Assertions.assertTrue(PredicateEnum.validOperator("="));
    Assertions.assertTrue(PredicateEnum.validOperator("!="));
    Assertions.assertTrue(PredicateEnum.validOperator("contains"));
    Assertions.assertTrue(PredicateEnum.validOperator("not_contains"));
    Assertions.assertTrue(PredicateEnum.validOperator("starts_with"));
    Assertions.assertTrue(PredicateEnum.validOperator("ends_with"));
    Assertions.assertTrue(PredicateEnum.validOperator("regex"));
    Assertions.assertTrue(PredicateEnum.validOperator("empty"));
    Assertions.assertTrue(PredicateEnum.validOperator("not_empty"));
    Assertions.assertTrue(PredicateEnum.validOperator("in"));
    Assertions.assertTrue(PredicateEnum.validOperator("not_in"));
    Assertions.assertTrue(PredicateEnum.validOperator(">"));
    Assertions.assertTrue(PredicateEnum.validOperator("<"));
    Assertions.assertTrue(PredicateEnum.validOperator(">="));
    Assertions.assertTrue(PredicateEnum.validOperator("<="));
  }

  @Test
  void testInvalidOperator() {
    Assertions.assertFalse(PredicateEnum.validOperator("invalid"));
    Assertions.assertFalse(PredicateEnum.validOperator(null));
  }

  @Test
  void testOperators() {
    Assertions.assertEquals(15, PredicateEnum.operators().size());
  }
}
