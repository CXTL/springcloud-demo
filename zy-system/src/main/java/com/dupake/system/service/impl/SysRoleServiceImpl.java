package com.dupake.system.service.impl;

import com.dupake.system.entity.SysRole;
import com.dupake.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysRoleServiceImpl  implements SysRoleService {

    @Override
    public SysRole selectByName(String roleName) {
        return null;
    }

    @Override
    public List<SysRole> findUserRoles(Long id) {
        return null;
    }
}
