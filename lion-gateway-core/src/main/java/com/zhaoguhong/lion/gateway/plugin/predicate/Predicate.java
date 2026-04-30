package com.zhaoguhong.lion.gateway.plugin.predicate;

/**
 * 数据断言接口
 *
 * @author zhaoguhong
 * @date 2021/11/19
 */
public interface Predicate {

  /**
   * 数据断言
   *
   * @param requestData 请求数据
   * @param targetData  需要比较的数据
   * @return
   */
  boolean test(String requestData, String targetData);

  /**
   * 操作符
   *
   * @return
   */
  String operator();

}
