package com.dupake.commodity.feign.hystrix;

import com.dupake.commodity.feign.OrderFeignService;
import com.dupake.common.message.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/17 15:22
 * @description
 */
@Component
public class HystrixClientFallback implements OrderFeignService {


    @Override
    public CommonResult hello() {
        return CommonResult.failed("call order hello error");
    }
}
