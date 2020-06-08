package com.dupake.system.service;

import com.dupake.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表  服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysMenuService {

    List<SysMenu> listByRoleId(Long roleId);
}
