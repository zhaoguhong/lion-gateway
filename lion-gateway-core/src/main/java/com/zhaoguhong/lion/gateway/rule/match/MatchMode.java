package com.zhaoguhong.lion.gateway.rule.match;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.List;

/**
 * Match mode
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public interface MatchMode {

  /**
   * Check if conditions match rules based on match mode
   *
   * @param requestContext   request context
   * @param conditionConfigs condition configuration list
   * @return true if match
   */
  boolean match(RequestContext requestContext, List<ConditionConfig> conditionConfigs);

  /**
   * Match mode name
   *
   * @return mode name
   */
  String mode();

}
