package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.system.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色菜单表  Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */

@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    void insertBatch(List<SysRoleMenu> sysRoleMenus);
}
