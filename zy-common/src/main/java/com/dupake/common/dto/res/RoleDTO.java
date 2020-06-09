package com.dupake.common.dto.res;

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
     * 权限
     */
    private List<MenuDTO> menuList;
}
