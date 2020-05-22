package com.dupake.system.service;

import com.dupake.system.entity.SysRole;
import com.dupake.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysRoleService.java
 * @Description TODO
 * @createTime 2020年05月17日 23:07:00
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id) {
        return roleMapper.selectById(id);
    }

    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }
}
