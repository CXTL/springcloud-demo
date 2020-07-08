package com.dupake.system.controller;


import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.message.CommonResult;
import com.dupake.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单表  前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Api(tags = "系统：菜单管理")
@RestController
@RequestMapping("/api/menus")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取权限列表(层级结构)")
    @GetMapping(value = "/treeList")
    public CommonResult<List<MenuDTO>> getUserInfo() {
        return sysMenuService.treeList();
    }


}
