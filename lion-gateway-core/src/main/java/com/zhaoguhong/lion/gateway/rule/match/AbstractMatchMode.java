package com.zhaoguhong.lion.gateway.rule.match;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.rule.loader.RequestDataLoader;
import com.zhaoguhong.lion.gateway.rule.loader.RequestDataLoaderFactory;
import com.zhaoguhong.lion.gateway.rule.predicate.Predicate;
import com.zhaoguhong.lion.gateway.rule.predicate.PredicateFactory;

/**
 * Base class for match modes
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public abstract class AbstractMatchMode implements MatchMode {

  /**
   * Single condition match
   *
   * @param requestContext  request context
   * @param conditionConfig condition configuration
   * @return true if match
   */
  protected boolean singleMatch(RequestContext requestContext, ConditionConfig conditionConfig) {
    RequestDataLoader requestDataLoader = RequestDataLoaderFactory
        .get(conditionConfig.getRequestDataType());

    String requestData = requestDataLoader.load(requestContext, conditionConfig);

    Predicate predicate = PredicateFactory.get(conditionConfig.getOperator());
    return predicate.test(requestData, conditionConfig.getTargetData());
  }

}
