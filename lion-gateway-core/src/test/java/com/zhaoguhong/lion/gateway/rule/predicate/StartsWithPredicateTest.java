package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StartsWithPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new StartsWithPredicate();
  }

  @Test
  void testStartsWith() {
    Assertions.assertTrue(predicate.test("hello world", "hello"));
    Assertions.assertTrue(predicate.test("abcde", "a"));
  }

  @Test
  void testNotStartsWith() {
    Assertions.assertFalse(predicate.test("hello world", "world"));
    Assertions.assertFalse(predicate.test(null, "hello"));
    Assertions.assertFalse(predicate.test("hello", null));
  }
}
