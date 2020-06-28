package com.dupake.system.controller;


import com.dupake.common.dto.req.user.UserAddRequest;
import com.dupake.common.dto.req.user.UserUpdateRequest;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
@RequestMapping("/api/users")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonResult<Map<String, Object>> getUserInfo() {
        return sysUserService.getUserInfo();
    }


    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/listByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "10"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "1"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "id desc")
    })
    public CommonResult<CommonPage<UserDTO>> listByPage(@ApiIgnore Pageable pageable) {
        return sysUserService.listByPage(pageable);
    }


    @ApiOperation("新增指定用户信息")
    @PostMapping(value = "/add")
    public CommonResult add(@Valid @RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
        return sysUserService.add(userAddRequest,request);
    }



    @ApiOperation("修改指定用户信息")
    @PostMapping(value = "/update")
    public CommonResult update(@Valid @RequestBody UserUpdateRequest userUpdateRequest, HttpServletRequest request) {
        return sysUserService.update(userUpdateRequest,request);
    }


    @ApiOperation("删除指定用户信息")
    @PostMapping(value = "/delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "userId") Long userId, HttpServletRequest request) {
        return sysUserService.delete(userId,request);
    }




}
