package com.dupake.report.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 下午6:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportAssetDTO implements Serializable {

    private static final long serialVersionUID = 7357946689693659207L;
    /**
     * 开始时间
     */
    @Excel(name = "开始时间",orderNum = "1", format = "yyyy-MM-dd HH:mm:ss",width = 20D)
    private String startTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", orderNum = "2",format = "yyyy-MM-dd HH:mm:ss",width = 20D)
    private String endTime;

    /**
     * 总收入
     */
    @Excel(name = "总收入", orderNum = "3",width = 20D)
    private BigDecimal totalIncome;

    /**
     * 总支出
     */
    @Excel(name = "总支出", orderNum = "4",width = 20D)
    private BigDecimal totalExpenditure;

    /**
     * 总利润
     */
    @Excel(name = "总利润", orderNum = "5",width = 20D)
    private BigDecimal totalProfit;

    /**
     * 总资产
     */
    @Excel(name = "总资产", orderNum = "6",width = 20D)
    private BigDecimal totalAsset;


}
