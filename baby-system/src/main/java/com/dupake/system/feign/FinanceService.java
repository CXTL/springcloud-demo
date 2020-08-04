package com.dupake.system.feign;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.system.feign.sentinel.HelloServiceFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "finance", fallbackFactory = HelloServiceFallbackFactory.class)
public interface FinanceService {


    @ApiOperation("查询全部帐套信息")
    @GetMapping(value = "/finance/account/listAll")
    public CommonResult<List<AccountDTO>> listAll();
}

