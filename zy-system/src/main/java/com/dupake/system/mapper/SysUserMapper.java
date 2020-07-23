package com.dupake.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.common.pojo.dto.req.user.UserQueryRequest;
import com.dupake.system.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */

public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 分页查询用户列表
     * @param userQueryRequest
     * @return
     */
    List<SysUser> selectUserListPage(UserQueryRequest userQueryRequest);

    /**
     * 批量删除用户
     * @param sysUsers
     */
    void updateBatch(List<SysUser> sysUsers);

    /**
     * 分页查询获取total数量
     * @param userQueryRequest
     * @return
     */
    int getTotalCount(UserQueryRequest userQueryRequest);
}
