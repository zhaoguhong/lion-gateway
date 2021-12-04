package com.zhaoguhong.lion.gateway.core.loader;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 请求数据加载工厂类
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class RequestDataLoaderFactory {

  private static Map<String, RequestDataLoader> loaders = Maps.newHashMap();

  public static RequestDataLoader get(String name) {
    return loaders.get(name);
  }

  @Autowired
  public void init(List<RequestDataLoader> loaderList) {
    loaderList
        .forEach(requestDataLoader -> loaders.put(requestDataLoader.type(), requestDataLoader));
  }

}
