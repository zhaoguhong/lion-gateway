package com.zhaoguhong.lion.gateway.core.match;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.List;

/**
 * 匹配模式
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public interface MatchMode {

  /**
   * 根据匹配模式判断条件是否符合规则
   *
   * @param requestContext   请求上下文
   * @param conditionConfigs 条件配置列表
   * @return
   */
  boolean match(RequestContext requestContext, List<ConditionConfig> conditionConfigs);

  /**
   * 匹配模式
   *
   * @return
   */
  String mode();

}
