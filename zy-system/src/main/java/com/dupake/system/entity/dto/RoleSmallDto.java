/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.system.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dupake
 * @date 2018-11-23
 */
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
