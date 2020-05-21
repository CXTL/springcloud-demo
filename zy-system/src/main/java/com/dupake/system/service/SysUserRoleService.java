package com.dupake.system.service;

import com.dupake.system.entity.SysUserRole;
import com.dupake.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUserRoleService.java
 * @Description TODO
 * @createTime 2020年05月17日 23:08:00
 */
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}