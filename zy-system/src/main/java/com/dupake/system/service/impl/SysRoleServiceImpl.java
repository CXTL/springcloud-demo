package com.dupake.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.system.entity.SysRole;
import com.dupake.system.mapper.SysRoleMapper;
import com.dupake.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole selectByName(String roleName) {
        return null;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findUserRoles(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectListByUserId(userId);
        return sysRoles;
    }
}
