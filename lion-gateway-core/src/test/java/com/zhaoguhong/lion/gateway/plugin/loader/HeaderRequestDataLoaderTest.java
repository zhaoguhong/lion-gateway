package com.zhaoguhong.lion.gateway.plugin.loader;


import static org.mockito.Mockito.*;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeaderRequestDataLoaderTest {

  private RequestDataLoader loader;

  private RequestContext requestContext;

  private ConditionConfig conditionConfig;

  private String headerName = UUID.randomUUID().toString();

  private String headerValue = UUID.randomUUID().toString();

  @Before
  public void setUp() {
    loader = new HeaderRequestDataLoader();
    requestContext = new RequestContext();
    HttpHeaders httpHeaders = mock(HttpHeaders.class);
    requestContext.setHttpHeaders(httpHeaders);
    when(httpHeaders.get(headerName)).thenReturn(headerValue);
    conditionConfig = ConditionConfig.builder().requestDataName(headerName).build();
  }

  @Test
  public void load() {
    Assert.assertEquals(loader.load(requestContext, conditionConfig), headerValue);
  }

}
