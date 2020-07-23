package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("SELECT r.* FROM sys_role r LEFT JOIN sys_user_role ur ON ur.role_id = r.id WHERE  ur.user_id = #{userId} and r.is_deleted = 0")
    List<SysRole> selectListByUserId(@Param("userId") Long userId);

    int getTotalCount(RoleQueryRequest roleQueryRequest);

    List<SysRole> selectListPage(RoleQueryRequest roleQueryRequest);
}
