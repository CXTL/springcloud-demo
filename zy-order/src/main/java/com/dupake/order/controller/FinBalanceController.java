package com.dupake.order.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.balance.BalanceAddRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceQueryRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceUpdateRequest;
import com.dupake.common.pojo.dto.res.BalanceDTO;
import com.dupake.order.service.FinBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-余额信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */

@Api(tags = "财务：余额信息")
@RestController
@RequestMapping("/api/fin/balance")
public class FinBalanceController {


    @Resource
    private FinBalanceService balanceService;

    @ApiOperation("分页查询帐套列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<BalanceDTO>> listByPage(@Valid @RequestBody BalanceQueryRequest balanceQueryRequest) {
        return balanceService.listByPage(balanceQueryRequest);
    }


    @ApiOperation("新增指定帐套信息")
    @PostMapping(value = "/addBalance")
    public CommonResult addBalance(@Valid @RequestBody BalanceAddRequest balanceAddRequest) {
        return balanceService.addBalance(balanceAddRequest);
    }



    @ApiOperation("修改指定帐套信息")
    @PostMapping(value = "/updateBalance")
    public CommonResult updateBalance(@Valid @RequestBody BalanceUpdateRequest balanceUpdateRequest) {
        return balanceService.updateBalance(balanceUpdateRequest);
    }




    @ApiOperation("批量删除帐套信息")
    @PostMapping(value = "/deleteBalance")
    public CommonResult deleteBalance(@RequestParam(value = "ids") List<Long> ids) {
        return balanceService.deleteBalance(ids);
    }
    
}
