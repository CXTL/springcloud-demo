package com.dupake.system.mapper;

import com.dupake.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUserRoleMapper.java
 * @Description TODO
 * @createTime 2020年05月17日 23:06:00
 */
@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
