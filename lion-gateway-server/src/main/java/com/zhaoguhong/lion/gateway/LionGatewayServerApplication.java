package com.zhaoguhong.lion.gateway;

import com.zhaoguhong.lion.gateway.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LionGatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LionGatewayServerApplication.class, args);
    NettyServer nettyServer = new NettyServer();
    nettyServer.start();
  }

}
