package com.zhaoguhong.lion.gateway.rule.match;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Match mode factory
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class MatchModeFactory {

  private static Map<String, MatchMode> matchModes = Maps.newHashMap();

  public static MatchMode get(String mode) {
    return matchModes.get(mode);
  }

  @Autowired
  public void init(List<MatchMode> matchModeList) {
    matchModeList
        .forEach(matchMode -> matchModes.put(matchMode.mode(), matchMode));
  }

}
