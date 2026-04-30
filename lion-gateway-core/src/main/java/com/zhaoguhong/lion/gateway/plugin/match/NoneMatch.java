package com.zhaoguhong.lion.gateway.plugin.match;

import com.zhaoguhong.lion.gateway.common.enums.MatchModeEnum;
import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 没有一个满足条件
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
@Component
public class NoneMatch extends AbstractMatchMode {

  @Override
  public boolean match(RequestContext requestContext, List<ConditionConfig> conditionConfigs) {
    return conditionConfigs.stream()
        .noneMatch(conditionConfig -> this.singleMatch(requestContext, conditionConfig));
  }

  @Override
  public String mode() {
    return MatchModeEnum.NONE.getMode();
  }
}
