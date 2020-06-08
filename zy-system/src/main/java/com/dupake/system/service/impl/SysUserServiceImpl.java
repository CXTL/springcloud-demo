package com.dupake.system.service.impl;

import com.dupake.system.entity.SysUser;
import com.dupake.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysUserServiceImpl  implements SysUserService {

    @Override
    public SysUser findUserByName(String name) {
        return null;
    }
}
