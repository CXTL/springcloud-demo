package com.dupake.finance.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.invest.InvestAddRequest;
import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.common.pojo.dto.req.invest.InvestUpdateRequest;
import com.dupake.common.pojo.dto.res.InvestDTO;
import com.dupake.finance.service.FinInvestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-投资信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Api(tags = "财务：投资信息")
@RestController
@RequestMapping("/api/fin/invest")
public class FinInvestController {

    @Resource
    private FinInvestService accountService;

    @ApiOperation("分页查询帐套列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<InvestDTO>> listByPage(@Valid @RequestBody InvestQueryRequest accountQueryRequest) {
        return accountService.listByPage(accountQueryRequest);
    }


    @ApiOperation("新增指定帐套信息")
    @PostMapping(value = "/addInvest")
    public CommonResult addInvest(@Valid @RequestBody InvestAddRequest accountAddRequest) {
        return accountService.addInvest(accountAddRequest);
    }



    @ApiOperation("修改指定帐套信息")
    @PostMapping(value = "/updateInvest")
    public CommonResult updateInvest(@Valid @RequestBody InvestUpdateRequest accountUpdateRequest) {
        return accountService.updateInvest(accountUpdateRequest);
    }




    @ApiOperation("批量删除帐套信息")
    @PostMapping(value = "/deleteInvest")
    public CommonResult deleteInvest(@RequestParam(value = "ids") List<Long> ids) {
        return accountService.deleteInvest(ids);
    }

}
