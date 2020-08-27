package com.dupake.finance.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dupake.common.pojo.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 财务-投资流水信息表
 * </p>
 *
 * @author dupake
 * @since 2020-08-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FinInvestFlow  extends BaseEntity implements Serializable {

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
     * 投资人id
     */
    private String investorId;

    /**
     * 实际投资金额
     */
    private BigDecimal actualInvestAmount;

    /**
     * 投资总额
     */
    private BigDecimal totalInvestAmount;

    /**
     * 应投资金额
     */
    private BigDecimal shouldInvestAmount;

    /**
     * 科目编码
     */
    private String subjectCode;

    /**
     * 投资比例 %
     */
    private Integer investRatio;

    /**
     * 投资日期
     */
    private Long investDate;



    /**
     * 是否删除 0:未删除1:已删除
     */
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;


}
