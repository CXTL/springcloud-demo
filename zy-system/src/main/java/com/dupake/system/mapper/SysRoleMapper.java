package com.dupake.system.mapper;

import com.dupake.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT * FROM sys_role WHERE name = #{name}")
    SysRole selectByName(String name);

    @Select("select s.* from sys_role s\n" +
            "left join sys_user_role su on s.id = role_id\n" +
            "where su.user_id = #{userId}")
    List<SysRole> selectByUserId(Integer userId);
}
