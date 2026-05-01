package com.zhaoguhong.lion.gateway.rule.loader;

import com.zhaoguhong.lion.gateway.common.enums.RequestDataEnum;
import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Load request parameter
 *
 * @author zhaoguhong
 * @date 2026/05/01
 */
@Component
public class ParamRequestDataLoader implements RequestDataLoader {

  @Override
  public String load(RequestContext requestContext, ConditionConfig conditionConfig) {
    String paramName = conditionConfig.getRequestDataName();
    Map<String, String> params = requestContext.getQueryParams();
    return params != null ? params.get(paramName) : null;
  }

  @Override
  public String type() {
    return RequestDataEnum.PARAM.getType();
  }
}
