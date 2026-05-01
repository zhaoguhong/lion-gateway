package com.zhaoguhong.lion.gateway.common.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestDataEnumTest {

  @Test
  void testValidType() {
    Assertions.assertTrue(RequestDataEnum.validType("header"));
    Assertions.assertTrue(RequestDataEnum.validType("path"));
    Assertions.assertTrue(RequestDataEnum.validType("method"));
    Assertions.assertTrue(RequestDataEnum.validType("param"));
  }

  @Test
  void testInvalidType() {
    Assertions.assertFalse(RequestDataEnum.validType("invalid"));
    Assertions.assertFalse(RequestDataEnum.validType(null));
  }

  @Test
  void testTypes() {
    Assertions.assertEquals(4, RequestDataEnum.types().size());
    Assertions.assertTrue(RequestDataEnum.types().contains("header"));
    Assertions.assertTrue(RequestDataEnum.types().contains("path"));
    Assertions.assertTrue(RequestDataEnum.types().contains("method"));
    Assertions.assertTrue(RequestDataEnum.types().contains("param"));
  }
}
