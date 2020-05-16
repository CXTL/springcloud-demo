package com.dupake.commodity.feign;

import com.dupake.common.message.Result;
import com.dupake.commodity.feign.hystrix.HystrixClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/17 15:09
 * @description
 */
@FeignClient(value = "order-service", fallback = HystrixClientFallback.class)
public interface OrderFeignService {

    @GetMapping("/test/hello")
    Result hello();
}
