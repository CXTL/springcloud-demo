package com.dupake.system.security;

/**
 * @ClassName JWTAuthorizationFilter
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 16:52
 */

import com.dupake.system.config.IgnoreUrlsConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录成功后 走此类进行鉴权操作
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在过滤之前和之后执行的事件
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String header = JwtConfig.getHeader();
        String authorization = request.getHeader("Authorization");
        String Referer = request.getHeader("Referer");
        String tokenHeader = request.getHeader(header);
        // 若请求头中没有Authorization信息 或是Authorization不以Bearer开头 或请求路径白名单的 则直接放行


        if (tokenHeader == null || !tokenHeader.startsWith(JwtConfig.getPrefix()))
        {
            chain.doFilter(request, response);
            return;
        }

        // 若请求头中有token 则调用下面的方法进行解析 并设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 从token中获取用户信息并新建一个token
     *
     * @param tokenHeader 字符串形式的Token请求头
     * @return 带用户名和密码以及权限的Authentication
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        // 去掉前缀 获取Token字符串
        String token = tokenHeader.replace(JwtConfig.getPrefix(), "");
        // 从Token中解密获取用户名
        String username = JwtTokenUtil.getUsername(token);
        // 从Token中解密获取用户角色
        String role = JwtTokenUtil.getUserRole(token);
        // 将[ROLE_XXX,ROLE_YYY]格式的角色字符串转换为数组
        String[] roles = StringUtils.strip(role, "[]").split(", ");
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (String s:roles)
        {
            authorities.add(new SimpleGrantedAuthority(s));
        }
        if (username != null)
        {
            return new UsernamePasswordAuthenticationToken(username, null,authorities);
        }
        return null;
    }
}
