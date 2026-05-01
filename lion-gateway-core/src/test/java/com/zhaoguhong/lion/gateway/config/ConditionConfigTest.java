package com.zhaoguhong.lion.gateway.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConditionConfigTest {

  @Test
  void testParseCondition() {
    ConditionConfig config = new ConditionConfig("header,userId|=|123");
    Assertions.assertEquals("header", config.getRequestDataType());
    Assertions.assertEquals("userId", config.getRequestDataName());
    Assertions.assertEquals("=", config.getOperator());
    Assertions.assertEquals("123", config.getTargetData());
  }

  @Test
  void testParseConditionWithoutName() {
    ConditionConfig config = new ConditionConfig("path|starts_with|/api");
    Assertions.assertEquals("path", config.getRequestDataType());
    Assertions.assertNull(config.getRequestDataName());
    Assertions.assertEquals("starts_with", config.getOperator());
    Assertions.assertEquals("/api", config.getTargetData());
  }

  @Test
  void testParseInvalidCondition() {
    ConditionConfig config = new ConditionConfig("invalid-format");
    Assertions.assertNull(config.getRequestDataType());
    Assertions.assertNull(config.getOperator());
    Assertions.assertNull(config.getTargetData());
  }
}
