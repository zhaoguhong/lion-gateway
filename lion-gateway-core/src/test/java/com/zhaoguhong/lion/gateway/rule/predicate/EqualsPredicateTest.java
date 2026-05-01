package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EqualsPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new EqualsPredicate();
  }

  @Test
  void testEqualValues() {
    Assertions.assertTrue(predicate.test("abc", "abc"));
    Assertions.assertTrue(predicate.test("", ""));
    Assertions.assertTrue(predicate.test(null, null));
  }

  @Test
  void testNotEqualValues() {
    Assertions.assertFalse(predicate.test("abc", "def"));
    Assertions.assertFalse(predicate.test("abc", null));
    Assertions.assertFalse(predicate.test(null, "abc"));
  }
}
