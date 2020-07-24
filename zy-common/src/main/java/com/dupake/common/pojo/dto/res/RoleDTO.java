package com.dupake.common.pojo.dto.res;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleDTO
 * @Description 角色DTO
 * @Author dupake
 * @Date 2020/6/9 15:29
 */
@Data
public class RoleDTO {
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

    /**
     * 创建时间
     */
    private Long createTime;


}
