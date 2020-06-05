/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.system.entity.vo;

import lombok.Data;

/**
 * 修改密码的 Vo 类
 * @author dupake
 * @date 2019年7月11日13:59:49
 */
@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}
