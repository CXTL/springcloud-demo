package com.dupake.system.service;

import com.dupake.system.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysUserService  {

    SysUser findUserByName(String name);
}
