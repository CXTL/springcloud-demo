package com.dupake.system.feign.sentinel;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.system.feign.FinanceService;

import java.util.List;

public class FinanceServiceFallback implements FinanceService {

    private Throwable throwable;

    FinanceServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * 调用服务提供方的输出接口.
     *
     * @return
     */
    @Override
    public CommonResult<List<AccountDTO>> listAll() {
        return CommonResult.failed("服务调用失败，降级处理。异常信息：" + throwable.getMessage());
    }
}

