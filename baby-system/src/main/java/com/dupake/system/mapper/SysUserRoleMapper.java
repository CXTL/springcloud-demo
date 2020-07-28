package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色关联表  Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void insertBatch(List<SysUserRole> sysUserRoles);
}
