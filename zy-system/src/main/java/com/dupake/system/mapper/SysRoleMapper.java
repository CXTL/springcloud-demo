package com.dupake.system.mapper;

import com.dupake.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysRoleMapper.java
 * @Description TODO
 * @createTime 2020年05月17日 23:06:00
 */
@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);
}
