/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package com.dupake.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dupake.common.annotation.Log;
import com.dupake.system.entity.Menu;
import com.dupake.system.entity.dto.MenuDto;
import com.dupake.system.entity.dto.MenuQueryCriteria;
import com.dupake.system.entity.dto.UserDto;
import com.dupake.system.service.MenuService;
import com.dupake.system.service.RoleService;
import com.dupake.system.service.UserService;
import com.dupake.system.utils.SecurityUtils;
import com.dupake.tools.exception.BadRequestException;
import com.dupake.tools.service.IGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dupake
 * @date 2018-12-03
 */
@Api(tags = "系统：菜单管理")
@RestController
@RequestMapping("/api/menus")
@SuppressWarnings("unchecked")
public class MenuController {

    @Resource
    private  MenuService menuService;

    @Resource
    private  UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private IGenerator generator;

    private static final String ENTITY_NAME = "menu";


    @Log("导出菜单数据")
    @ApiOperation("导出菜单数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('menu:list')")
    public void download(HttpServletResponse response, MenuQueryCriteria criteria) throws IOException {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        menuService.download(generator.convert(menuService.queryAll(criteria),MenuDto.class), response);
    }

    @ApiOperation("获取前端所需菜单")
    @GetMapping(value = "/build")
    public ResponseEntity<Object> buildMenus(){
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        List<MenuDto> menuDtoList = menuService.findByRoles(roleService.findByUsersId(user.getId()));
        List<MenuDto> menuDtos = (List<MenuDto>) menuService.buildTree(menuDtoList).get("content");
        return new ResponseEntity<>(menuService.buildMenus(menuDtos), HttpStatus.OK);
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/tree")
    @PreAuthorize("@el.check('menu:list','roles:list')")
    public ResponseEntity<Object> getMenuTree(){
        return new ResponseEntity<>(menuService.getMenuTree(menuService.findByPid(0L)), HttpStatus.OK);
    }

    @Log("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("@el.check('menu:list')")
    public ResponseEntity<Object> getMenus(MenuQueryCriteria criteria){
        List<MenuDto> menuDtoList = generator.convert(menuService.queryAll(criteria),MenuDto.class);
        return new ResponseEntity<>(menuService.buildTree(menuDtoList), HttpStatus.OK);
    }

    @Log("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("@el.check('menu:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Menu resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }

        return new ResponseEntity<>(menuService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("@el.check('menu:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Menu resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        menuService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("@el.check('menu:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        Set<Menu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<Menu> menuList = menuService.findByPid(id);
            menuSet.add(menuService.getOne(new QueryWrapper<Menu>().eq("id",id)));
            menuSet = menuService.getDeleteMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
