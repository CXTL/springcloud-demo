package com.dupake.system.service;

import com.dupake.system.entity.SysUser;
import com.dupake.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUserService.java
 * @Description TODO
 * @createTime 2020年05月17日 23:07:00
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}

