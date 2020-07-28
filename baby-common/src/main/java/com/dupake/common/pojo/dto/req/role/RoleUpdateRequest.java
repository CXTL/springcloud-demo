package com.dupake.common.pojo.dto.req.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserUpdateRequest
 * @Description 修改 用户 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class RoleUpdateRequest implements Serializable {

    private static final long serialVersionUID = 4371186875731167316L;

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否启用 1:是 0:否
     */
    private Integer isEnable;

    /**
     * 备注
     */
    private String remark;

}
