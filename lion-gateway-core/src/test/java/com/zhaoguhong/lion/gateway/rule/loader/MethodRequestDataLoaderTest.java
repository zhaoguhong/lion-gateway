package com.zhaoguhong.lion.gateway.rule.loader;


import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodRequestDataLoaderTest {

  private RequestDataLoader loader;

  private RequestContext requestContext;

  private ConditionConfig conditionConfig;

  private String method = "GET";

  @BeforeEach
  void setUp() {
    loader = new MethodRequestDataLoader();
    requestContext = new RequestContext();
    requestContext.setMethod(method);
    conditionConfig = ConditionConfig.builder().build();
  }

  @Test
  void load() {
    Assertions.assertEquals(loader.load(requestContext, conditionConfig), method);
  }

}
