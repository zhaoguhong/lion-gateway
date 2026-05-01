package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotEqualsPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new NotEqualsPredicate();
  }

  @Test
  void testNotEqualValues() {
    Assertions.assertTrue(predicate.test("abc", "def"));
    Assertions.assertTrue(predicate.test("abc", null));
    Assertions.assertTrue(predicate.test(null, "abc"));
  }

  @Test
  void testEqualValues() {
    Assertions.assertFalse(predicate.test("abc", "abc"));
    Assertions.assertFalse(predicate.test("", ""));
    Assertions.assertFalse(predicate.test(null, null));
  }
}
