package com.dupake.common.pojo.dto.res.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 首页表格数据
 * @Authror xt
 * @Date 2020/7/28 下午2:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeTableDTO implements Serializable {

    private static final long serialVersionUID = -4197083162522656630L;

    /**
     * 今日收入
     */
    private BigDecimal incomeToday;
    /**
     * 昨日收入
     */
    private BigDecimal incomeYesterday;

    /**
     * 收入比例 昨日收入 / 今日收入
     */
    private double rateIncome;

    /**
     * 今日支出
     */
    private BigDecimal expenditureToday;
    /**
     * 昨日支出
     */
    private BigDecimal expenditureYesterday;

    /**
     * 支出比例 昨日支出 / 今日支出
     */
    private double rateExpenditure;

    /**
     * 今日利润
     */
    private BigDecimal profitToday;
    /**
     *昨日利润
     */
    private BigDecimal profitYesterday;
    /**
     * 利润比例 昨日利润 / 今日利润
     */
    private double rateProfit;

    /**
     * 今日资产
     */
    private BigDecimal assetToday;
    /**
     *昨日资产
     */
    private BigDecimal assetYesterday;
    /**
     * 资产 昨日资产 / 今日资产
     */
    private double rateAsset;


}
