package com.zhaoguhong.lion.gateway.rule.loader;


import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParamRequestDataLoaderTest {

  private RequestDataLoader loader;

  private RequestContext requestContext;

  private ConditionConfig conditionConfig;

  private String paramName = UUID.randomUUID().toString();

  private String paramValue = UUID.randomUUID().toString();

  @BeforeEach
  void setUp() {
    loader = new ParamRequestDataLoader();
    requestContext = new RequestContext();
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put(paramName, paramValue);
    requestContext.setQueryParams(queryParams);
    conditionConfig = ConditionConfig.builder().requestDataName(paramName).build();
  }

  @Test
  void load() {
    Assertions.assertEquals(loader.load(requestContext, conditionConfig), paramValue);
  }

  @Test
  void loadNullParams() {
    requestContext.setQueryParams(null);
    Assertions.assertNull(loader.load(requestContext, conditionConfig));
  }

  @Test
  void loadNonExistentParam() {
    conditionConfig.setRequestDataName("nonExistent");
    Assertions.assertNull(loader.load(requestContext, conditionConfig));
  }

}
