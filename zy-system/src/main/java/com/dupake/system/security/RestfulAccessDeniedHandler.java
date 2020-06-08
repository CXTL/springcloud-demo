package com.dupake.system.security;

/**
 * @ClassName MyAccessDeniedHandler
 * @Description  * Spring security权限不足处理类
 *  * 只有登录后（即接口有传token）接口权限不足才会进入AccessDeniedHandler，
 *  * 如果是未登陆或者会话超时等，不会触发AccessDeniedHandler，而是会直接跳转到登陆页面。
 * @Author dupake
 * @Date 2020/5/25 16:40
 */

import cn.hutool.json.JSONUtil;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        System.out.println("权限不足：" + e.getMessage());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Result.error(BaseResult.FORBIDDEN.getCode(), BaseResult.FORBIDDEN.getMessage())));
        response.getWriter().flush();
    }
}
