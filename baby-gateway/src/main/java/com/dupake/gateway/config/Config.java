package com.dupake.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/22 15:02
 * @description
 */
@Configuration
public class Config {

    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }


//    @Bean
//    @Primary
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }

//    @Bean
//    @Primary
//    public KeyResolver apiKeyResolver() {
//        //URL限流,超出限流返回429状态
//        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
//    }
}
