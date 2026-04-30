package com.zhaoguhong.lion.gateway.plugin.match;

import com.zhaoguhong.lion.gateway.common.enums.MatchModeEnum;
import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 任何一个满足
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Component
public class AnyMatch extends AbstractMatchMode {

  @Override
  public boolean match(RequestContext requestContext, List<ConditionConfig> conditionConfigs) {
    return conditionConfigs.stream()
        .anyMatch(conditionConfig -> this.singleMatch(requestContext, conditionConfig));
  }

  @Override
  public String mode() {
    return MatchModeEnum.OR.getMode();
  }
}
