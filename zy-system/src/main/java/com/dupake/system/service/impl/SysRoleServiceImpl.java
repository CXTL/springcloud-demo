package com.dupake.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.exception.BadRequestException;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.role.RoleAddRequest;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.common.pojo.dto.req.role.RoleUpdateRequest;
import com.dupake.common.pojo.dto.res.MenuDTO;
import com.dupake.common.pojo.dto.res.RoleDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.system.entity.SysMenu;
import com.dupake.system.entity.SysRole;
import com.dupake.system.entity.SysUser;
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
import java.util.Objects;
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
     *
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
        checkRoleInfo(roleAddRequest.getName());

        //落地菜单数据
        try {
            sysRoleMapper.insert(
                    SysRole.builder()
                            .name(roleAddRequest.getName())
                            .isEnable(roleAddRequest.getIsEnable())
                            .remark(roleAddRequest.getRemark())
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
        checkRoleInfo(roleUpdateRequest.getName());
        //修改菜单信息
        try {
            sysRoleMapper.updateById(
                    SysRole.builder()
                            .name(roleUpdateRequest.getName())
                            .isEnable(roleUpdateRequest.getIsEnable())
                            .remark(roleUpdateRequest.getRemark())
                            .id(roleUpdateRequest.getId())
                            .build());
        } catch (Exception e) {
            log.error("SysRoleServiceImpl update role error , param:{}, error:{}", JSONObject.toJSONString(roleUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * 校验角色名称信息
     * @param name
     */
    private void checkRoleInfo(String name){
        if(!Objects.isNull(sysRoleMapper.selectOne(
                new LambdaQueryWrapper<SysRole>()
                        .eq(SysRole::getName,name)
                        .eq(SysRole::getIsDeleted, YesNoSwitchEnum.NO.getValue())))){
            log.error("role name:{} is exist",name);
            throw new BadRequestException(BaseResult.SYS_ROLE_NAME_IS_EXIST.getCode(), BaseResult.SYS_ROLE_NAME_IS_EXIST.getMessage());
        }
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult delete(List<Long> ids) {
        //todo 管理员角色校验
        try {
            List<SysRole> sysUsers = ids.stream().map(a -> {
                SysRole sysRole = SysRole.builder().build();
                sysRole.setId(a);
                sysRole.setIsDeleted(YesNoSwitchEnum.YES.getValue());
                sysRole.setUpdateTime(DateUtil.getCurrentTimeMillis());
                return sysRole;
            }).collect(Collectors.toList());

            sysRoleMapper.updateBatch(sysUsers);

        }catch (Exception e){
            log.error("SysRoleServiceImpl delete role error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        return CommonResult.success();
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
