package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表  Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectListByUserId(@Param("userId") Long userId);
}
