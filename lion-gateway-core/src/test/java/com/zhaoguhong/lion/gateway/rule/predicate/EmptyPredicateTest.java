package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmptyPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new EmptyPredicate();
  }

  @Test
  void testEmpty() {
    Assertions.assertTrue(predicate.test(null, null));
    Assertions.assertTrue(predicate.test(null, ""));
    Assertions.assertTrue(predicate.test("", null));
    Assertions.assertTrue(predicate.test("", ""));
  }

  @Test
  void testNotEmpty() {
    Assertions.assertFalse(predicate.test("hello", null));
    Assertions.assertFalse(predicate.test("hello", ""));
  }
}
