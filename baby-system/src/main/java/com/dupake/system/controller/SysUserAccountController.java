package com.dupake.system.controller;


import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.common.pojo.dto.res.system.RoleDTO;
import com.dupake.common.pojo.dto.res.system.UserAccountDTO;
import com.dupake.system.service.SysUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户帐套关联表  前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Api(tags = "系统：帐套管理")
@RestController
@RequestMapping("/system/account")
public class SysUserAccountController {


    @Resource
    private SysUserAccountService sysUserAccountService;


    @ApiOperation("查询帐套信息列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<AccountDTO>> listAll() {
        {
            return sysUserAccountService.listAll();
        }
    }

    @ApiOperation("查询帐套信息列表")
    @GetMapping(value = "/getAccountByAdmin")
    public CommonResult<List<UserAccountDTO>> getAccountByAdmin(@RequestParam(value = "userId") Long userId) {
        {
            return sysUserAccountService.getAccountByAdmin(userId);
        }
    }

}
