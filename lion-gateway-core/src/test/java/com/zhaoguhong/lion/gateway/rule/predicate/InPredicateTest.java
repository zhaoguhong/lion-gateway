package com.zhaoguhong.lion.gateway.rule.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InPredicateTest {

  private Predicate predicate;

  @BeforeEach
  void setUp() {
    predicate = new InPredicate();
  }

  @Test
  void testIn() {
    Assertions.assertTrue(predicate.test("apple", "apple,banana,orange"));
    Assertions.assertTrue(predicate.test("banana", "apple,banana,orange"));
  }

  @Test
  void testNotIn() {
    Assertions.assertFalse(predicate.test("grape", "apple,banana,orange"));
    Assertions.assertFalse(predicate.test(null, "apple,banana"));
    Assertions.assertFalse(predicate.test("apple", null));
  }
}
