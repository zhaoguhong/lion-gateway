package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotContainsPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new NotContainsPredicate();
  }

  @Test
  void testNotContains() {
    Assertions.assertTrue(predicate.test("hello world", "foo"));
    Assertions.assertTrue(predicate.test(null, "world"));
    Assertions.assertTrue(predicate.test("hello", null));
  }

  @Test
  void testContains() {
    Assertions.assertFalse(predicate.test("hello world", "world"));
    Assertions.assertFalse(predicate.test("abcde", "abc"));
  }
}
