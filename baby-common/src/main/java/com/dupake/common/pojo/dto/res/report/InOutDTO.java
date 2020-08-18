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
public class InOutDTO {


    /**
     * 总收入
     */
    private BigDecimal totalIncome;

    /**
     * 总支出
     */
    private BigDecimal totalExpenditure;


    /**
     *收支日期
     */
    private String assetDate;

    /**
     * 日期
     */
    private String date;


    public String getDate() {
        return getAssetDate();
    }
}
