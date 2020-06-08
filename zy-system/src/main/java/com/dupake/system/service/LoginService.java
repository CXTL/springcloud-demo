package com.dupake.system.service;

import com.dupake.common.vo.LoginVO;
import com.dupake.system.entity.SysUser;
import com.dupake.system.security.JwtConfig;
import com.dupake.system.security.JwtTokenUtil;
import com.dupake.system.security.UserDetailsServiceImpl;
import com.dupake.system.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 11:18
 */

/**
 * @Description:
 * @Author: Mt.Li
 */

@Service
public class LoginService {

    @Autowired
    SysUserService userService;

    @Autowired
    SysRoleService roleService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    JwtConfig jwtConfig;

    public Map login(LoginVO loginVO) throws UsernameNotFoundException {
        SysUser dbUser = this.findUserByName(loginVO.getUsername());
        // 用户不存在 或者 密码错误
        if (dbUser == null || !dbUser.getUsername().equals(loginVO.getUsername())
                || !MD5Util.string2MD5(loginVO.getPassword()).equals(dbUser.getPassword())) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 用户已被封禁
        if (0 == dbUser.getStatus()) {
            throw new RuntimeException("你已被封禁");
        }

        // 用户名 密码匹配，获取用户详细信息（包含角色Role）
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginVO.getUsername());

        // 根据用户详细信息生成token
        final String token = JwtTokenUtil.createToken(loginVO.getUsername(), userDetails.getAuthorities().toString());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) { // SimpleGrantedAuthority是GrantedAuthority实现类
            // GrantedAuthority包含类型为String的获取权限的getAuthority()方法
            // 提取角色并放入List中
            roles.add(authority.getAuthority());
        }

        Map<String, Object> map = new HashMap<>(3);

        map.put("token", jwtConfig.getPrefix() + token);
        map.put("name", loginVO.getUsername());
        map.put("roles", roles);

        //将token存入redis(TOKEN_username, Bearer + token, jwt存放五天 过期时间) jwtConfig.time 单位[s]
        redisTemplate.opsForValue().
                set(JwtConfig.REDIS_TOKEN_KEY_PREFIX + loginVO.getUsername(), jwtConfig.getPrefix() + token, jwtConfig.getTime(), TimeUnit.SECONDS);

        return map;

    }

    /**
     * 根据用户名查询用户
     */
    public SysUser findUserByName(String name) {
        return userService.findUserByName(name);
    }


}
