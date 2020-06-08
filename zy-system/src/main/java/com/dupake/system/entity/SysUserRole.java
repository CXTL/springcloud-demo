package com.dupake.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUserRole.java
 * @Description TODO
 * @createTime 2020年05月17日 23:04:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole {
    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;
}
