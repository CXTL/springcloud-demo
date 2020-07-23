package com.dupake.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dupake.common.exception.BadRequestException;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.role.RoleAddRequest;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.common.pojo.dto.req.role.RoleUpdateRequest;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.pojo.dto.res.RoleDTO;
import com.dupake.system.entity.SysMenu;
import com.dupake.system.entity.SysRole;
import com.dupake.system.mapper.SysRoleMapper;
import com.dupake.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


    /**
     * 分页列表查询
     * @param roleQueryRequest
     * @return
     */
    @Override
    public CommonResult<CommonPage<RoleDTO>> listByPage(RoleQueryRequest roleQueryRequest) {
        List<RoleDTO> roleDTOS = new ArrayList<>();

        int totalCount = sysRoleMapper.getTotalCount(roleQueryRequest);
        if (totalCount > 0) {
            List<SysRole> sysRoles = sysRoleMapper.selectListPage(roleQueryRequest);
            if (!ObjectUtils.isEmpty(sysRoles)) {
                roleDTOS = sysRoles.stream().map(a -> {
                    RoleDTO roleDTO = new RoleDTO();
                    BeanUtils.copyProperties(a, roleDTO);
                    return roleDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(roleDTOS, totalCount));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addRole(RoleAddRequest roleAddRequest) {
        // 角色 名称校验

        //落地菜单数据
        try {
            sysRoleMapper.insert(SysRole.builder()
                    .build());
        } catch (Exception e) {
            log.error("SysMenuServiceImpl add menu error , param:{}, error:{}", JSONObject.toJSONString(roleAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateRole(RoleUpdateRequest roleUpdateRequest) {
        //角色名称校验

        //修改菜单信息
        try {
            sysRoleMapper.updateById(SysRole.builder()
                    .build());
        } catch (Exception e) {
            log.error("SysMenuServiceImpl update menu error , param:{}, error:{}", JSONObject.toJSONString(roleUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }


        return CommonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult delete(Long roleId) {

        //管理员角色校验

        //修改角色状态

        //删除用户角色关系

        //删除角色权限关系

        //修改角色状态

        return null;
    }

    @Override
    public CommonResult deleteBatch(List<Long> ids) {
        //管理员角色校验

        //修改角色状态

        //删除用户角色关系

        //删除角色权限关系

        //批量修改角色状态

        return null;
    }


    @Override
    public SysRole selectByName(String roleName) {
        return null;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> findUserRoles(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectListByUserId(userId);
        return sysRoles;
    }
    /**
     * 根据用户ID查询权限
     *
     * @param id
     * @return
     */
    @Override
    public List<MenuDTO> getMenuListByUserId(Long id) {
        return null;
    }

   
}
