package com.dupake.common.pojo.dto.req.invest;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName AccountUpdateRequest
 * @Description 修改 菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class InvestUpdateRequest implements Serializable {

    private static final long serialVersionUID = 4371186875731167316L;

    private Long id;


    /**
     * 帐套信息编码
     */
    private String accountCode;


    /**
     * 科目编码
     */
    private String subjectCode;

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
