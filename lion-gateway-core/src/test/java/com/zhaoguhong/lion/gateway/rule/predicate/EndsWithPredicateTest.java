package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EndsWithPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new EndsWithPredicate();
  }

  @Test
  void testEndsWith() {
    Assertions.assertTrue(predicate.test("hello world", "world"));
    Assertions.assertTrue(predicate.test("abcde", "e"));
  }

  @Test
  void testNotEndsWith() {
    Assertions.assertFalse(predicate.test("hello world", "hello"));
    Assertions.assertFalse(predicate.test(null, "world"));
    Assertions.assertFalse(predicate.test("hello", null));
  }
}
