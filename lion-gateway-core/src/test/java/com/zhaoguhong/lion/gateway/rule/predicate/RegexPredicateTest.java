package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new RegexPredicate();
  }

  @Test
  void testRegexMatch() {
    Assertions.assertTrue(predicate.test("hello123", ".*\\d+"));
    Assertions.assertTrue(predicate.test("abc123def", "abc\\d+def"));
  }

  @Test
  void testRegexNoMatch() {
    Assertions.assertFalse(predicate.test("hello", "\\d+"));
    Assertions.assertFalse(predicate.test(null, ".*"));
    Assertions.assertFalse(predicate.test("hello", null));
  }
}
