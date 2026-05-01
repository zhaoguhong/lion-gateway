package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotEmptyPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new NotEmptyPredicate();
  }

  @Test
  void testNotEmpty() {
    Assertions.assertTrue(predicate.test("hello", null));
    Assertions.assertTrue(predicate.test("hello", ""));
  }

  @Test
  void testEmpty() {
    Assertions.assertFalse(predicate.test(null, null));
    Assertions.assertFalse(predicate.test(null, ""));
    Assertions.assertFalse(predicate.test("", null));
    Assertions.assertFalse(predicate.test("", ""));
  }
}
