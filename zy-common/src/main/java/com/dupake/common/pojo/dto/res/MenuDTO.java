package com.dupake.common.pojo.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PermissionDTO
 * @Description 权限DTO
 * @Author dupake
 * @Date 2020/6/9 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO  implements Serializable {

    private static final long serialVersionUID = -6225606748663424690L;
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否隐藏 0:未隐藏1:已隐藏
     */
    private Integer hidden;

    /**
     * 权限
     */
    private String permission;

    /**
     * 类型 0:菜单权限1:按钮权限
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 子菜单权限
     */
    private List<MenuDTO> children;

}
