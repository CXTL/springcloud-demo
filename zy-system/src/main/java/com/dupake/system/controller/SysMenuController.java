package com.dupake.system.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.menu.MenuAddRequest;
import com.dupake.common.pojo.dto.req.menu.MenuQueryRequest;
import com.dupake.common.pojo.dto.req.menu.MenuUpdateRequest;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public CommonResult<List<MenuDTO>> treeList() {
        return sysMenuService.treeList();
    }


    @ApiOperation("分页查询菜单列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<MenuDTO>> listByPage(@Valid @RequestBody MenuQueryRequest menuQueryRequest) {
        return sysMenuService.listByPage(menuQueryRequest);
    }


    @ApiOperation("新增指定菜单信息")
    @PostMapping(value = "/addMenu")
    public CommonResult addMenu(@Valid @RequestBody MenuAddRequest menuAddRequest) {
        return sysMenuService.addMenu(menuAddRequest);
    }



    @ApiOperation("修改指定菜单信息")
    @PostMapping(value = "/updateMenu")
    public CommonResult updateMenu(@Valid @RequestBody MenuUpdateRequest menuUpdateRequest) {
        return sysMenuService.updateMenu(menuUpdateRequest);
    }




    @ApiOperation("批量删除菜单信息")
    @PostMapping(value = "/deleteMenu")
    public CommonResult deleteMenu(@RequestParam(value = "ids") List<Long> ids) {
        return sysMenuService.deleteMenu(ids);
    }


    @ApiOperation("查询角色相关菜单")
    @GetMapping(value = "/listMenuByRoleId")
    public CommonResult<List<MenuDTO>> listMenuByRoleId(@RequestParam(value = "roleId") Long roleId) {
        {
            return sysMenuService.listMenuByRoleId(roleId);
        }
    }

}
