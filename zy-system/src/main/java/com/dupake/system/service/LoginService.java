package com.dupake.system.service;

import com.dupake.system.JwtTokenUtil;
import com.dupake.system.entity.SysUser;
import com.dupake.system.security.JwtConfig;
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
    MyUserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtConfig jwtConfig;

    public Map login(LoginRequest loginRequest) throws RuntimeException{
        SysUser dbUser = this.findUserByName(loginRequest.getUsername());
        // 用户不存在 或者 密码错误
        System.out.println(MD5Util.string2MD5(dbUser.getPassword()) + "============"+ loginRequest.getPassword());
        System.out.println(dbUser.getName() + "============"+ loginRequest.getUsername());
        if (dbUser == null || !dbUser.getName().equals(loginRequest.getUsername()) || !MD5Util.string2MD5(loginRequest.getPassword()).equals(dbUser.getPassword())) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 用户已被封禁
        if (0 == dbUser.getState()) {
            throw new RuntimeException("你已被封禁");
        }

        // 用户名 密码匹配，获取用户详细信息（包含角色Role）
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // 根据用户详细信息生成token
        final String token = jwtTokenUtil.generateToken(userDetails);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) { // SimpleGrantedAuthority是GrantedAuthority实现类
            // GrantedAuthority包含类型为String的获取权限的getAuthority()方法
            // 提取角色并放入List中
            roles.add(authority.getAuthority());
        }

        Map<String, Object> map = new HashMap<>(3);

        map.put("token", jwtConfig.getPrefix() + token);
        map.put("name", loginRequest.getUsername());
        map.put("roles", roles);

        //将token存入redis(TOKEN_username, Bearer + token, jwt存放五天 过期时间) jwtConfig.time 单位[s]
        redisTemplate.opsForValue().
                set(JwtConfig.REDIS_TOKEN_KEY_PREFIX + loginRequest.getUsername(), jwtConfig.getPrefix() + token, jwtConfig.getTime(), TimeUnit.SECONDS);

        return map;

    }

    /**
     * 根据用户名查询用户
     */
    public SysUser findUserByName(String name) {
        return userService.findUserByName(name);
    }


}
