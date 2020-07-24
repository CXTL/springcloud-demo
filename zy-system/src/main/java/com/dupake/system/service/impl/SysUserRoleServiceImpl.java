package com.dupake.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.CommonResult;
import com.dupake.common.utils.DateUtil;
import com.dupake.system.entity.SysUserRole;
import com.dupake.system.mapper.SysUserRoleMapper;
import com.dupake.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 分配用户角色信息
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult allocRole(Long userId, List<Long> roleIds) {
        //删除原来的用户角色关联关系
        int delete = sysUserRoleMapper.delete(new LambdaUpdateWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));

        if(!CollectionUtils.isEmpty(roleIds)){
            List<SysUserRole> sysUserRoles = roleIds.stream().map(a -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(a);
                sysUserRole.setUserId(userId);
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserRoleMapper.insertBatch(sysUserRoles);
        }


        return CommonResult.success();
    }
}
