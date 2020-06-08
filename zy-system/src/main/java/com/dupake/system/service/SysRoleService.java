package com.dupake.system.service;

import com.dupake.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysRoleService {

    SysRole selectByName(String roleName);

    List<SysRole> findUserRoles(Long id);
}
