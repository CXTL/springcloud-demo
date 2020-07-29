package com.dupake.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 报表-资产报表(每天统计)
 * </p>
 *
 * @author dupake
 * @since 2020-07-28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 总资产/天
     */
    private BigDecimal totalAsset;

    /**
     * 总利润/天
     */
    private BigDecimal totalProfit;

    /**
     * 总收入/天
     */
    private BigDecimal totalIncome;

    /**
     * 总支出/天
     */
    private BigDecimal totalExpenditure;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 是否删除 0:未删除1:已删除
     */
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;

}
