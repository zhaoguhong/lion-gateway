package com.zhaoguhong.lion.gateway.rule.loader;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;

/**
 * Request data loader
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public interface RequestDataLoader {

  /**
   * Load request data by condition
   *
   * @param requestContext  request context
   * @param conditionConfig condition configuration
   * @return request data value
   */
  String load(RequestContext requestContext, ConditionConfig conditionConfig);

  /**
   * Request data type
   *
   * @return type name
   */
  String type();

}
