package com.dupake.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//注册到consul
public class HmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmGatewayApplication.class, args);
    }

}
