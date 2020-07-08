package com.dupake.system.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisPlusConfig
 * @Description mybatis plus 分页配置
 * @Author dupake
 * @Date 2020/6/8 20:03
 */

@Configuration
public class MyBatisPlusConfig {

    /*
     * @ClassName MyBatisPlusConfig
     * @Desc TODO   mybatis-plus 配置拦截
     * @Date 2019/3/26 18:13
     * @Version 1.0
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置方言
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

}
