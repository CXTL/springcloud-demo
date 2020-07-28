package com.dupake.system.security;

import com.dupake.system.entity.SysMenu;
import com.dupake.system.service.SysMenuService;
import com.dupake.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomPermissionEvaluator
 * @Description 自定义权限校验
 * @Author dupake
 * @Date 2020/6/8 16:02
 */

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) jwtUser.getAuthorities();

        // 遍历用户所有角色
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Long roleId = roleService.selectByName(roleName).getId();
            // 得到角色所有的权限
            List<SysMenu> permissionList = sysMenuService.listByRoleId(roleId);

            // 遍历permissionList
            for (SysMenu sysPermission : permissionList) {
                // 获取权限集
                // 如果访问的Url和权限用户符合的话，返回true
                if (targetUrl.equals(sysPermission.getPermission())) {
                    return true;
                }
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
