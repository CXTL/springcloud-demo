package com.dupake.system.feign;

import com.dupake.system.feign.sentinel.HelloServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order", fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloService {

    /**
     * 调用服务提供方的输出接口.
     *
     * @param str 用户输入
     * @return hello result
     */
    @GetMapping("/hello/{str}")
    String hello(@PathVariable("str") String str);
}

