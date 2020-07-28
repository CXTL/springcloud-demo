package com.dupake.system.controller;


import com.dupake.common.pojo.dto.req.user.UserAddRequest;
import com.dupake.common.pojo.dto.req.user.UserQueryRequest;
import com.dupake.common.pojo.dto.req.user.UserUpdateRequest;
import com.dupake.common.pojo.dto.res.UserDTO;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/system/users")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonResult<Map<String, Object>> getUserInfo() {
        return sysUserService.getUserInfo();
    }


    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @PostMapping(value = "/listByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "10")
    })
    public CommonResult<CommonPage<UserDTO>> listByPage(@RequestBody UserQueryRequest userQueryRequest) {
        return sysUserService.listByPage(userQueryRequest);
    }


    @ApiOperation("新增指定用户信息")
    @PostMapping(value = "/addUser")
    public CommonResult addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
        return sysUserService.addUser(userAddRequest);
    }



    @ApiOperation("修改指定用户信息")
    @PostMapping(value = "/updateUser")
    public CommonResult updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return sysUserService.updateUser(userUpdateRequest);
    }


    @ApiOperation("批量删除用户信息")
    @PostMapping(value = "/deleteUser")
    public CommonResult deleteUser(@RequestParam(value = "ids") List<Long> ids) {
        return sysUserService.deleteUser(ids);
    }


    @ApiOperation("分配用户角色信息")
    @PostMapping(value = "/allocRole")
    public CommonResult allocRole(@RequestParam(value = "userId") Long userId ,
                                  @RequestParam(value = "roleIds") List<Long> roleIds) {
        return sysUserService.allocRole(userId,roleIds);
    }




}
