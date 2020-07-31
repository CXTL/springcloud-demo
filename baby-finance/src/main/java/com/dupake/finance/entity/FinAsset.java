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
 * 财务-余额信息表
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
public class FinAsset extends BaseEntity implements Serializable {

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
     * 总余额
     */
    private BigDecimal totalBalance;

    /**
     * 冻结余额
     */
    private BigDecimal freezeBalance;

    /**
     * 可用余额
     */
    private BigDecimal availableBalance;

    /**
     * 状态 0:正常 1:异常
     */
    private Integer status;

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
