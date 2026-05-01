package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotInPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new NotInPredicate();
  }

  @Test
  void testNotIn() {
    Assertions.assertTrue(predicate.test("grape", "apple,banana,orange"));
    Assertions.assertTrue(predicate.test(null, "apple,banana"));
    Assertions.assertTrue(predicate.test("apple", null));
  }

  @Test
  void testIn() {
    Assertions.assertFalse(predicate.test("apple", "apple,banana,orange"));
    Assertions.assertFalse(predicate.test("banana", "apple,banana,orange"));
  }
}
