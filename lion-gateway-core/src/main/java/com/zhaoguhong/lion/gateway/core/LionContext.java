package com.zhaoguhong.lion.gateway.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 项目上下文类
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Component
public class LionContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  public static <T> T getBean(Class<T> requiredType) {
    return applicationContext.getBean(requiredType);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    LionContext.applicationContext = applicationContext;
  }

}
