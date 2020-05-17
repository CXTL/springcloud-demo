package com.dupake.generator.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author dupake
 * @since 2020-05-17
 */
public class TUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TUserRole{" +
            "userId=" + userId +
            ", roleId=" + roleId +
        "}";
    }
}
