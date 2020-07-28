package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.common.pojo.dto.req.menu.MenuQueryRequest;
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

    /**
     * 查询菜单列表
     * @param userId
     * @return
     */
    List<SysMenu> selectListByUserId(@Param("userId") Long userId);

    /**
     * 分页查询菜单列表
     * @param menuQueryRequest
     * @return
     */
    List<SysMenu> selectListPage(MenuQueryRequest menuQueryRequest);

    /**
     * 查询菜单列表总数
     * @param menuQueryRequest
     * @return
     */
    int getTotalCount(MenuQueryRequest menuQueryRequest);

    /**
     * 批量修改
     * @param sysMenuList
     */
    void updateBatch(List<SysMenu> sysMenuList);

    /**
     * 根据角色ID查询关联菜单
     * @param roleId
     * @return
     */
    List<SysMenu> listMenuByRoleId(@Param("roleId") Long roleId);
}
