package com.dupake.commodity.mapper;

import com.dupake.commodity.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 17:00
 * @description
 */
@Mapper
public interface SysUserMapper {
    SysUser findInfoById(Integer id);

    List<SysUser> findList();

    void save(SysUser user);

    void update(SysUser user);

    void delete(Integer id);
}
