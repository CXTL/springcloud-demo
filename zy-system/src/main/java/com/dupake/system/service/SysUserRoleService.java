package com.dupake.system.service;

import com.dupake.common.message.CommonResult;

import java.util.List;

/**
 * <p>
 * 用户角色关联表  服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysUserRoleService{

    CommonResult allocRole(Long userId, List<Long> roleIds);
}
