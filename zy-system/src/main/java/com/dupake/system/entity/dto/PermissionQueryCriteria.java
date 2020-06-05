/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.system.entity.dto;

import com.dupake.common.annotation.Query;
import lombok.Data;

/**
 * 公共查询类
 */
@Data
public class PermissionQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,alias")
    private String blurry;
}
