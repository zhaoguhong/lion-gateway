package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GreaterThanOrEqualPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new GreaterThanOrEqualPredicate();
  }

  @Test
  void testGreaterOrEqual() {
    Assertions.assertTrue(predicate.test("100", "50"));
    Assertions.assertTrue(predicate.test("50", "50"));
    Assertions.assertTrue(predicate.test("10.5", "10"));
  }

  @Test
  void testLess() {
    Assertions.assertFalse(predicate.test("50", "100"));
    Assertions.assertFalse(predicate.test(null, "50"));
    Assertions.assertFalse(predicate.test("abc", "50"));
  }
}
