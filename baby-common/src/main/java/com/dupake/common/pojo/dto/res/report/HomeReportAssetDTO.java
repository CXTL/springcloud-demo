package com.dupake.common.pojo.dto.res.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/29 下午7:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeReportAssetDTO {

    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 总收入
     */
    private BigDecimal totalIncome;

    /**
     * 总支出
     */
    private BigDecimal totalExpenditure;

    /**
     * 总资产
     */
    private BigDecimal totalAsset;

    /**
     * 总利润
     */
//    private BigDecimal totalProfit;
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
    /**
     *
     */
    private String date;


    public String getDate() {
        return getStartTime();
    }
}
