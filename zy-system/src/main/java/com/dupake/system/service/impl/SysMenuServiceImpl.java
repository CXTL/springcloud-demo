package com.dupake.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.menu.MenuAddRequest;
import com.dupake.common.pojo.dto.req.menu.MenuQueryRequest;
import com.dupake.common.pojo.dto.req.menu.MenuUpdateRequest;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.system.entity.SysMenu;
import com.dupake.system.mapper.SysMenuMapper;
import com.dupake.system.service.SysMenuService;
import com.dupake.system.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {


    @Resource
    private SysMenuMapper sysMenuMapper;


    /**
     * @param menuQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.MenuDTO>>
     * @Description 分页查询菜单列表
     **/
    @Override
    public CommonResult<CommonPage<MenuDTO>> listByPage(MenuQueryRequest menuQueryRequest) {
        List<MenuDTO> menuDTOS = new ArrayList<>();

        int totalCount = sysMenuMapper.getTotalCount(menuQueryRequest);
        if (totalCount > 0) {
            List<SysMenu> sysMenus = sysMenuMapper.selectListPage(menuQueryRequest);
            if (!ObjectUtils.isEmpty(sysMenus)) {
                menuDTOS = sysMenus.stream().map(a -> {
                    MenuDTO menuDTO = new MenuDTO();
                    BeanUtils.copyProperties(a, menuDTO);
                    return menuDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(menuDTOS, totalCount));
    }


    /**
     * @param menuAddRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 新增菜单
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addMenu(MenuAddRequest menuAddRequest) {
        //菜单名称校验 权限标识校验
        checkMenuInfo(menuAddRequest.getName(), menuAddRequest.getPermission(),null);

        //落地菜单数据
        try {
            sysMenuMapper.insert(SysMenu.builder()
                    .name(menuAddRequest.getName())
                    .hidden(menuAddRequest.getHidden())
                    .path(menuAddRequest.getPath())
                    .permission(menuAddRequest.getPermission())
                    .remark(menuAddRequest.getRemark())
                    .pid(menuAddRequest.getPid())
                    .sort(menuAddRequest.getSort())
                    .type(menuAddRequest.getType())
                    .icon(menuAddRequest.getIcon())
                    .build());
        } catch (Exception e) {
            log.error("SysMenuServiceImpl add menu error , param:{}, error:{}", JSONObject.toJSONString(menuAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * @param menuUpdateRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 修改菜单
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateMenu(MenuUpdateRequest menuUpdateRequest) {

        SysMenu sysMenu = sysMenuMapper.selectOne(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getId, menuUpdateRequest.getId())
                .eq(SysMenu::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(sysMenu)){
            log.error("menu is null");
            throw new BadRequestException(BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getCode(), BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getMessage());
        }
        //菜单名称校验 权限标识校验
        checkMenuInfo(menuUpdateRequest.getName(), menuUpdateRequest.getPermission(),sysMenu);

        //修改菜单信息
        try {
            sysMenuMapper.updateById(SysMenu.builder()
                    .name(menuUpdateRequest.getName())
                    .hidden(menuUpdateRequest.getHidden())
                    .path(menuUpdateRequest.getPath())
                    .permission(menuUpdateRequest.getPermission())
                    .remark(menuUpdateRequest.getRemark())
                    .pid(menuUpdateRequest.getPid())
                    .sort(menuUpdateRequest.getSort())
                    .type(menuUpdateRequest.getType())
                    .icon(menuUpdateRequest.getIcon())
                    .id(menuUpdateRequest.getId())
                    .build());
        } catch (Exception e) {
            log.error("SysMenuServiceImpl update menu error , param:{}, error:{}", JSONObject.toJSONString(menuUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }


        return CommonResult.success();
    }

    /**
     * @param ids :
     * @return com.dupake.common.message.CommonResult
     * @Description
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteMenu(List<Long> ids) {
        //查询菜单及所有子菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectList(
                new LambdaUpdateWrapper<SysMenu>()
                        .eq(SysMenu::getIsDeleted, YesNoSwitchEnum.NO.getValue())
                        .in(SysMenu::getPid, ids)
        );
        if (!CollectionUtil.isEmpty(sysMenus)) {
            log.error("menu has sub menus");
            throw new BadRequestException(BaseResult.SYS_MENU_DELETE_ERROR_EXIST_SUB_MENU.getCode(),
                    BaseResult.SYS_MENU_DELETE_ERROR_EXIST_SUB_MENU.getMessage());
        }
        //批量修改菜单状态
        List<SysMenu> sysMenuList = ids.stream().map(a -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            sysMenu.setUpdateTime(DateUtil.getCurrentTimeMillis());
            sysMenu.setId(a);
            return sysMenu;
        }).collect(Collectors.toList());

        try {
            sysMenuMapper.updateBatch(sysMenuList);
        } catch (Exception e) {
            log.error("SysMenuServiceImpl update menu error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        //todo 修改角色菜单表数据
        return CommonResult.success();
    }


    /**
     * 校验菜单名称和权限标识
     *
     * @param name
     * @param permission
     */
    private void checkMenuInfo(String name, String permission,SysMenu menu) {
        if(Objects.isNull(menu) || !menu.getName().equals(name)){
            if (!Objects.isNull(sysMenuMapper.selectOne(new LambdaUpdateWrapper<SysMenu>()
                    .eq(SysMenu::getName, name)
                    .eq(SysMenu::getIsDeleted, YesNoSwitchEnum.NO.getValue())
            ))) {
                log.error("menu name:{} is exist", name);
                throw new BadRequestException(BaseResult.SYS_MENU_NAME_IS_EXIST.getCode(),
                        BaseResult.SYS_MENU_NAME_IS_EXIST.getMessage());

            }
        }
        if(Objects.isNull(menu) || !menu.getPermission().equals(permission)){
            if (!Objects.isNull(sysMenuMapper.selectOne(new LambdaUpdateWrapper<SysMenu>()
                    .eq(SysMenu::getPermission, permission)
                    .eq(SysMenu::getIsDeleted, YesNoSwitchEnum.NO.getValue())
            ))) {
                log.error("menu permission:{} is exist", permission);
                throw new BadRequestException(BaseResult.SYS_MENU_PERMISSION_IS_EXIST.getCode(),
                        BaseResult.SYS_MENU_PERMISSION_IS_EXIST.getMessage());

            }
        }

    }


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


    /**
     * 递归菜单树
     *
     * @param sysMenuList
     * @return
     */
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
