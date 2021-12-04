package com.zhaoguhong.lion.gateway.core;

import io.netty.handler.codec.http.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 请求上下文
 *
 * @author zhaoguhong
 * @date 2021/11/22
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestContext {

  /**
   * header 参数
   */
  private HttpHeaders httpHeaders;

  private String uri;

}
