package com.dupake.common.pojo.dto.res.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午9:31
 */
@Data
public class UserAccountDTO implements Serializable {


    private static final long serialVersionUID = 7469449521533979599L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 帐套名称
     */
    private String accountName;


}
