package com.dupake.commodity.controller;

import com.dupake.common.message.Result;
import com.dupake.commodity.entity.SysUser;
import com.dupake.commodity.service.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:53
 * @description
 */
@RestController
@Api(tags = "用户控制中心")
@RequestMapping(value = "authUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息 - for-web")
    @GetMapping(value = "/findInfoById")
    public Result findInfoById(@RequestParam(value = "id") Integer id) {
        SysUser authUser = sysUserService.findInfoById(id);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "查询用户列表", notes = "查询用户列表 - for-web")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> list = sysUserService.findList(pageNum, pageSize);
        return Result.ok(list);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户 - for-web")
    @PostMapping(value = "/save")
    public Result save(@RequestBody SysUser user) {
        SysUser authUser = sysUserService.save(user);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "修改用户", notes = "修改用户 - for-web")
    @PostMapping(value = "/update")
    public Result update(@RequestBody SysUser user) {
        SysUser authUser = sysUserService.update(user);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户 - for-web")
    @PostMapping(value = "/delete")
    public Result deleteByIds(@RequestParam Integer id) {
        sysUserService.deleteByIds(id);
        return Result.ok();
    }

}
