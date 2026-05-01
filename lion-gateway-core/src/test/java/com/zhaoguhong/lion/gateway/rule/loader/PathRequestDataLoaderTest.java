package com.zhaoguhong.lion.gateway.rule.loader;


import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathRequestDataLoaderTest {

  private RequestDataLoader loader;

  private RequestContext requestContext;

  private ConditionConfig conditionConfig;

  private String path = "/test/" + UUID.randomUUID().toString();

  @BeforeEach
  void setUp() {
    loader = new PathRequestDataLoader();
    requestContext = new RequestContext();
    requestContext.setPath(path);
    conditionConfig = ConditionConfig.builder().build();
  }

  @Test
  void load() {
    Assertions.assertEquals(loader.load(requestContext, conditionConfig), path);
  }

}
