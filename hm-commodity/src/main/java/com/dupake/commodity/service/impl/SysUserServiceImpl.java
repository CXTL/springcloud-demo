package com.dupake.commodity.service.impl;

import com.dupake.commodity.entity.SysUser;
import com.dupake.commodity.mapper.SysUserMapper;
import com.dupake.commodity.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:59
 * @description
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findInfoById(Integer id) {
        return sysUserMapper.findInfoById(id);
    }

    @Override
    public PageInfo<SysUser> findList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.findList();
        PageInfo<SysUser> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public SysUser save(SysUser user) {
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        sysUserMapper.save(user);
        return user;
    }

    @Override
    public SysUser update(SysUser user) {
        user.setUpdateTime(System.currentTimeMillis());
        sysUserMapper.update(user);
        return null;
    }

    @Override
    public void deleteByIds(Integer id) {
        sysUserMapper.delete(id);
    }
}
