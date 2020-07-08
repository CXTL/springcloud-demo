package com.dupake.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysMenu;
import com.dupake.system.mapper.SysMenuMapper;
import com.dupake.system.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Resource
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> listByRoleId(Long roleId) {
        return null;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<MenuDTO> listByUserId(Long userId) {
        return null;
    }

    /**
     * 获取菜单权限列表
     *
     * @return
     */
    @Override
    public CommonResult<List<MenuDTO>> treeList() {

        List<SysMenu> sysMenuList = sysMenuMapper.selectList(
                new LambdaUpdateWrapper<SysMenu>().eq(SysMenu::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        List<MenuDTO> menuDTOS = conventMenu(sysMenuList);

        return CommonResult.success(menuDTOS);
    }


    private List<MenuDTO> conventMenu(List<SysMenu> sysMenuList) {
        List<MenuDTO> menuDTOS = new ArrayList<>(sysMenuList.size());
        //递归获取权限树
        if (!CollectionUtils.isEmpty(sysMenuList)) {
            menuDTOS = sysMenuList.stream()
                    .filter(menu -> menu.getPid().equals(0L))
                    .map(menu -> covert(menu, sysMenuList)).collect(Collectors.toList());

        }
        return menuDTOS;
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<MenuDTO> getMenuListByUserId(Long userId) {

        List<SysMenu> sysMenus = sysMenuMapper.selectListByUserId(userId);
        List<MenuDTO> menuDTOS = conventMenu(sysMenus);
        return menuDTOS;
    }

    /**
     * 将权限转换为带有子集的权限对象
     * 当找不到子级权限的时候map操作不会递归调用
     *
     * @param menu
     * @param sysMenuList
     * @return
     */
    private MenuDTO covert(SysMenu menu, List<SysMenu> sysMenuList) {
        MenuDTO dto = MenuDTO.builder().build();
        BeanUtils.copyProperties(menu, dto);
        List<MenuDTO> children = sysMenuList.stream()
                .filter(subMenu -> subMenu.getPid().equals(menu.getId()))
                .map(subMenu -> covert(subMenu, sysMenuList)).collect(Collectors.toList());
        dto.setChildren(children);
        return dto;
    }
}
