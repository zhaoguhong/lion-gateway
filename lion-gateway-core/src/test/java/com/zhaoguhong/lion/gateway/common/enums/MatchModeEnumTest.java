package com.zhaoguhong.lion.gateway.common.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchModeEnumTest {

  @Test
  void testValidMode() {
    Assertions.assertTrue(MatchModeEnum.validMode("and"));
    Assertions.assertTrue(MatchModeEnum.validMode("or"));
    Assertions.assertTrue(MatchModeEnum.validMode("none"));
  }

  @Test
  void testInvalidMode() {
    Assertions.assertFalse(MatchModeEnum.validMode("invalid"));
    Assertions.assertFalse(MatchModeEnum.validMode(null));
  }

  @Test
  void testModes() {
    Assertions.assertEquals(3, MatchModeEnum.modes().size());
    Assertions.assertTrue(MatchModeEnum.modes().contains("and"));
    Assertions.assertTrue(MatchModeEnum.modes().contains("or"));
    Assertions.assertTrue(MatchModeEnum.modes().contains("none"));
  }
}
