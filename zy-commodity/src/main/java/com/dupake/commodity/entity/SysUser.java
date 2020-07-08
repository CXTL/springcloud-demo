package com.dupake.commodity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:48
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private Long createTime;
    private Long updateTime;
}
