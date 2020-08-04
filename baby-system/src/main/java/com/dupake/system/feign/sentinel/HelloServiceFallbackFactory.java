package com.dupake.system.feign.sentinel;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallbackFactory implements FallbackFactory<FinanceServiceFallback> {

    @Override
    public FinanceServiceFallback create(Throwable throwable) {
        return new FinanceServiceFallback(throwable);
    }
}
