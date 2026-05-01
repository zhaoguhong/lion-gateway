package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContainsPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new ContainsPredicate();
  }

  @Test
  void testContains() {
    Assertions.assertTrue(predicate.test("hello world", "world"));
    Assertions.assertTrue(predicate.test("abcde", "abc"));
  }

  @Test
  void testNotContains() {
    Assertions.assertFalse(predicate.test("hello world", "foo"));
    Assertions.assertFalse(predicate.test(null, "world"));
    Assertions.assertFalse(predicate.test("hello", null));
  }
}
