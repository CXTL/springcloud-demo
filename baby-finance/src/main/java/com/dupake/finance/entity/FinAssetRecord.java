package com.dupake.finance.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dupake.common.pojo.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 财务-余额记录信息表
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FinAssetRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资产ID
     */
    private Long assetId;

    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 科目编码
     */
    private String subjectCode;

    /**
     * 应收金额
     */
    private BigDecimal receiveAmount;

    /**
     * 应付金额
     */
    private BigDecimal payAmount;

    /**
     * 实收金额
     */
    private BigDecimal realReceiveAmount;

    /**
     * 实付金额
     */
    private BigDecimal realPayAmount;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 变动前余额
     */
    private BigDecimal balanceBefore;

    /**
     * 变动后余额
     */
    private BigDecimal balanceAfter;

    /**
     * 类型 1 收入 2 支出
     */
    private Integer type;

    /**
     * 是否删除 0:未删除1:已删除
     */
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;
    /**
     * 收支日期
     */
    private Long assetDate;

}
