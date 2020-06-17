package com.dupake.system.service;

import com.dupake.common.dto.req.LoginRequest;
import com.dupake.common.message.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName LoginService
 * @Description 登录service
 * @Author dupake
 * @Date 2020/6/17 10:07
 */
public interface LoginService {

    public CommonResult<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest req);

    public CommonResult sentEmailCode(String email);

}
