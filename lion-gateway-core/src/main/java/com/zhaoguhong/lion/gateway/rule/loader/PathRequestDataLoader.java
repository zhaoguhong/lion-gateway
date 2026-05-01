package com.zhaoguhong.lion.gateway.rule.loader;

import com.zhaoguhong.lion.gateway.common.enums.RequestDataEnum;
import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import org.springframework.stereotype.Component;

/**
 * Load request path
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class PathRequestDataLoader implements RequestDataLoader {

  @Override
  public String load(RequestContext requestContext, ConditionConfig conditionConfig) {
    return requestContext.getPath();
  }

  @Override
  public String type() {
    return RequestDataEnum.PATH.getType();
  }
}
