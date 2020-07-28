package com.dupake.common.pojo.dto.res.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class InvestDTO implements Serializable {

    private static final long serialVersionUID = -6225606748663424690L;
    private Long id;

    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 投资人名称
     */
    private String investName;

    /**
     * 投资款
     */
    private BigDecimal investFund;

    /**
     * 投资总额
     */
    private BigDecimal investAmount;

    /**
     * 投资比例 %
     */
    private Integer investRatio;


    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;

}
