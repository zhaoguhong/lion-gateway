package com.zhaoguhong.lion.gateway.rule.loader;

import com.zhaoguhong.lion.gateway.common.enums.RequestDataEnum;
import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import org.springframework.stereotype.Component;

/**
 * Get header from request parameters
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class HeaderRequestDataLoader implements RequestDataLoader {

  @Override
  public String load(RequestContext requestContext, ConditionConfig conditionConfig) {
    String headerName = conditionConfig.getRequestDataName();
    return requestContext.getHttpHeaders().get(headerName);
  }

  @Override
  public String type() {
    return RequestDataEnum.HEADER.getType();
  }
}
