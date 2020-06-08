package com.dupake.system.mapper;

import com.dupake.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUserMapper.java
 * @Description TODO
 * @createTime 2020年05月17日 23:05:00
 */
@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);
}









