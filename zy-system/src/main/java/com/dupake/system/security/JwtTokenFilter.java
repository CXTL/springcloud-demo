package com.dupake.system.security;

/**
 * @ClassName MyJwtTokenFilter
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 15:45
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 过滤器，在这里解析token，拿到该用户角色，设置到springsecurity的上下文环境中，让springsecurity自动判断权限
 * 所有请求最先进入此过滤器，包括登录接口，而且在springsecurity的密码验证之前执行
 * * GYB
 * * 20190220
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Resource
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("jwt.header")
    private String header;

    @Value("jwt.prefix")
    private String prefix;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(header);
        if (authHeader != null && authHeader.startsWith(prefix)) {
            String authToken = authHeader.substring(prefix.length());
            String username = JwtTokenUtil.getUsername(authToken);
            System.out.println("username:" + username);
            //验证token,具体怎么验证看需求，可以只验证token不查库，把权限放在jwt中即可
            UserDetails UserDetails = userDetailsServiceImpl.loadUserByUsername(username);
            if (JwtTokenUtil.isExpiration(authToken)) {//token过期
                System.out.println("token过期" + authToken);
            } else {
                System.out.println("token没过期，放行" + authToken);
                //这里只要告诉springsecurity权限即可，账户密码就不用提供验证了，这里我们把UserDetails传给springsecurity，以便以后我们获取当前登录用户
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(UserDetails, null, UserDetails.getAuthorities());
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null, UserDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info(String.format("Authenticated userDetail %s, setting security context", username));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}