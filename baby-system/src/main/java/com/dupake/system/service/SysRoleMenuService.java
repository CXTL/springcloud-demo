package com.dupake.system.service;

import com.dupake.common.message.CommonResult;

import java.util.List;

/**
 * <p>
 * 角色菜单表  服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysRoleMenuService {

    CommonResult allocMenu(Long roleId, List<Long> menuIds);
}
