package com.dupake.finance.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.account.AccountAddRequest;
import com.dupake.common.pojo.dto.req.account.AccountQueryRequest;
import com.dupake.common.pojo.dto.req.account.AccountUpdateRequest;
import com.dupake.common.pojo.dto.res.AccountDTO;
import com.dupake.finance.service.FinAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-帐套信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Api(tags = "财务：帐套信息")
@RestController
@RequestMapping("/api/fin/account")
public class FinAccountController {


    @Resource
    private FinAccountService accountService;

    @ApiOperation("分页查询帐套列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<AccountDTO>> listByPage(@Valid @RequestBody AccountQueryRequest accountQueryRequest) {
        return accountService.listByPage(accountQueryRequest);
    }


    @ApiOperation("新增指定帐套信息")
    @PostMapping(value = "/addAccount")
    public CommonResult addAccount(@Valid @RequestBody AccountAddRequest accountAddRequest) {
        return accountService.addAccount(accountAddRequest);
    }



    @ApiOperation("修改指定帐套信息")
    @PostMapping(value = "/updateAccount")
    public CommonResult updateAccount(@Valid @RequestBody AccountUpdateRequest accountUpdateRequest) {
        return accountService.updateAccount(accountUpdateRequest);
    }




    @ApiOperation("批量删除帐套信息")
    @PostMapping(value = "/deleteAccount")
    public CommonResult deleteAccount(@RequestParam(value = "ids") List<Long> ids) {
        return accountService.deleteAccount(ids);
    }

}
