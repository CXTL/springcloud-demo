package com.dupake.system.security;

import com.dupake.system.JwtTokenUtil;
import com.dupake.system.service.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring security登录成功处理类,返回jwt
 *  * GYB
 *  * 20190220
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
            System.out.println("登录成功");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");

            //从authentication中获取用户信息
            final JwtUser userDetail = (JwtUser) authentication.getPrincipal();
            //生成jwt
            String token =  jwtTokenUtil.createJwtToken(userDetail.getUsername());
            httpServletResponse.addHeader("token", "Bearer " + token);
            httpServletResponse.getWriter().write("{\"result\":\"ok\"}");
            httpServletResponse.getWriter().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
