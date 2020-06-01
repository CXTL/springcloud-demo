package com.dupake.system.service;

import com.dupake.system.entity.SysRole;
import com.dupake.system.entity.SysUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyUserDetailsService
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 11:28
 */
@Service
public  class MyUserDetailsService implements UserDetailsService {

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = userService.findUserByName(name);
        // 新建权限集合，SimpleGrantedAuthority是GrantedAuthority实现类
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(1);
        //用于添加用户的权限。将用户权限添加到authorities
        List<SysRole> roles = roleService.findUserRoles(user.getId()); // 查询该用户的角色
        for (SysRole role : roles) {
            // 将role的name放入权限的集合
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        JwtUser JwtUser = new JwtUser(name,user.getPassword(),authorities);
        if (JwtUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username."));
        }
        System.out.println(JwtUser);
        return JwtUser;
    }
}
