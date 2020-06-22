package com.dupake.system.controller;

import com.dupake.common.dto.req.LoginRequest;
import com.dupake.common.message.CommonResult;
import com.dupake.system.service.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName LoginController.java
 * @Description 登录控制器
 * @createTime 2020年05月17日 23:08:00
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "系统：登录管理")
public class LoginController {

    @Resource
    private LoginServiceImpl loginServiceImpl;

    /**
     * 登录返回token
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest request) {
        return loginServiceImpl.login(loginRequest, request);
    }


    /**
     * 发送邮箱验证码
     */
    @ApiOperation("发送邮箱验证码")
    @PostMapping("/sentEmailCode")
    public CommonResult sentEmailCode(@RequestParam(value = "email") String email) {
        return loginServiceImpl.sentEmailCode(email);
    }


}
