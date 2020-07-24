package com.dupake.common.pojo.dto.req.role;

import lombok.Data;

/**
 * @ClassName UserAddRequest
 * @Description 新增角色 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class RoleAddRequest {


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
