package com.dupake.system.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.pojo.dto.req.menu.MenuAddRequest;
import com.dupake.common.pojo.dto.req.menu.MenuQueryRequest;
import com.dupake.common.pojo.dto.req.menu.MenuUpdateRequest;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表  服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysMenuService {

    /**
     * 获取菜单列表
     * @param roleId
     * @return
     */
    List<SysMenu> listByRoleId(Long roleId);

    /**
     * 获取菜单列表
     * @param id
     * @return
     */
    List<MenuDTO> listByUserId(Long id);

    /**
     * 获取菜单树
     * @param
     * @return
     */
    CommonResult<List<MenuDTO>> treeList();

    /**
     * 获取菜单列表
     * @param id
     * @return
     */
    List<MenuDTO> getMenuListByUserId(Long id);

    /**
     * 分页查询
     * @param menuQueryRequest
     * @return
     */
    CommonResult<CommonPage<MenuDTO>> listByPage(MenuQueryRequest menuQueryRequest);

    /**
     * 添加菜单
     * @param menuAddRequest
     * @return
     */
    CommonResult addMenu(MenuAddRequest menuAddRequest);

    /**
     * 修改菜单
     * @param menuUpdateRequest
     * @return
     */
    CommonResult updateMenu(MenuUpdateRequest menuUpdateRequest);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    CommonResult delete(Long menuId);

    /**
     * 批量删除菜单
     * @param ids
     * @return
     */
    CommonResult deleteBatch(List<Long> ids);
}
