package com.dupake.common.pojo.dto.req.invest;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AccountAddRequest
 * @Description 新增菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class InvestAddRequest {



    /**
     * 帐套编码
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
}
