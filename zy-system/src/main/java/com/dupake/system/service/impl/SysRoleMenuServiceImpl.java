package com.dupake.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysRoleMenu;
import com.dupake.system.entity.SysUserRole;
import com.dupake.system.mapper.SysRoleMenuMapper;
import com.dupake.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysRoleMenuServiceImpl  implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 分配菜单角色
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public CommonResult allocMenu(Long roleId, List<Long> menuIds) {
        //删除角色关联菜单
        //删除原来的用户角色关联关系
        int delete = sysRoleMenuMapper.delete(
                new LambdaUpdateWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));

        if(!CollectionUtils.isEmpty(menuIds)){
            List<SysRoleMenu> sysRoleMenus = menuIds.stream().map(a -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(a);
                return sysRoleMenu;
            }).collect(Collectors.toList());
            sysRoleMenuMapper.insertBatch(sysRoleMenus);
        }


        return CommonResult.success();
    }
}
