package com.zhaoguhong.lion.gateway.core.match;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import com.zhaoguhong.lion.gateway.core.loader.RequestDataLoader;
import com.zhaoguhong.lion.gateway.core.loader.RequestDataLoaderFactory;
import com.zhaoguhong.lion.gateway.core.predicate.Predicate;
import com.zhaoguhong.lion.gateway.core.predicate.PredicateFactory;

/**
 * 匹配模式的基类
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public abstract class AbstractMatchMode implements MatchMode {

  /**
   * 单个条件匹配
   *
   * @param requestContext  请求上下文
   * @param conditionConfig 条件配置
   * @return
   */
  protected boolean singleMatch(RequestContext requestContext, ConditionConfig conditionConfig) {
    RequestDataLoader requestDataLoader = RequestDataLoaderFactory
        .get(conditionConfig.getRequestDataType());

    String requestData = requestDataLoader.load(requestContext, conditionConfig);

    Predicate predicate = PredicateFactory.get(conditionConfig.getOperator());
    return predicate.test(requestData, conditionConfig.getTargetData());
  }

}
