package com.dupake.system.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanConfig
 * @Description 将一些不方便加@Component注解的类放在此处加入spring容器
 * @Author dupake
 * @Date 2020/5/25 10:10
 */
@Component
public class BeanConfig {

    /**
     * spring-security加密方法
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * spring-boot内置的json工具
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}