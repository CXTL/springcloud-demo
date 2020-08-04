package com.dupake.common.pojo.dto.res.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 资产详情数据
 * @Authror xt
 * @Date 2020/7/28 下午2:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetInfoDTO implements Serializable {


    private static final long serialVersionUID = 6154018918939771509L;

    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 帐套信息名称
     */
    private String accountName;

    /**
     * 科目编码
     */
    private String subjectCode;
    /**
     * 科目编码
     */
    private String subjectName;


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
    private BigDecimal totalProfit;
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;



}
