package com.zhaoguhong.lion.gateway.core.loader;

import com.zhaoguhong.lion.gateway.config.ConditionConfig;
import com.zhaoguhong.lion.gateway.core.RequestContext;

/**
 * 请求数据加载器
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
public interface RequestDataLoader {

  /**
   * 根据条件加载请求数据
   *
   * @param requestContext  请求上下文
   * @param conditionConfig 条件配置
   * @return
   */
  String load(RequestContext requestContext, ConditionConfig conditionConfig);

  /**
   * 请求数据类型
   *
   * @return
   */
  String type();

}
