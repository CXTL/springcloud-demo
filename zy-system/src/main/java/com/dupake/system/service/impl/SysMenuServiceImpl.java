package com.dupake.system.service.impl;

import com.dupake.common.dto.res.MenuDTO;
import com.dupake.system.entity.SysMenu;
import com.dupake.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysMenuServiceImpl  implements SysMenuService {

    @Override
    public List<SysMenu> listByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<MenuDTO> listByUserId(Long id) {
        return null;
    }
}
