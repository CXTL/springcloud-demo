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
public class HomeAssetDTO implements Serializable {

    private static final long serialVersionUID = -4197083162522656630L;

    /**
     * 本月总资产
     */
    private BigDecimal totalAssetMonth;
    /**
     * 总资产同比上月比例
     */
    private BigDecimal rateAssetMonth;
    /**
     * 本周总资产
     */
    private BigDecimal totalAssetWeek;
    /**
     * 总资产同比上周比例
     */
    private BigDecimal rateAssetWeek;
    /**
     * 本月总利润
     */
    private BigDecimal totalProfitMonth;
    /**
     *本月总利润同比上月总利润比例
     */
    private BigDecimal rateProfitMonth;
    /**
     * 本周总利润
     */
    private BigDecimal totalProfitWeek;
    /**
     * 本周总利润同比上周总利润比例
     */
    private BigDecimal rateProfitWeek;

}
