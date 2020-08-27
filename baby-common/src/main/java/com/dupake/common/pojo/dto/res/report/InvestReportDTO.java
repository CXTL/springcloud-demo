package com.dupake.common.pojo.dto.res.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/27 上午10:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestReportDTO implements Serializable {

    private static final long serialVersionUID = 2468691736506483935L;
    /**
     * 投资人姓名
     */
    private String investName;
    /**
     * 投资日期
     */
    private Long investDate;
    /**
     * 实际投资金额
     */
    private BigDecimal investFund;
    /**
     * 投资总金额
     */
    private BigDecimal investAmount;
    /**
     * 应投资总额
     */
    private BigDecimal shouldInvestAmount;


}
