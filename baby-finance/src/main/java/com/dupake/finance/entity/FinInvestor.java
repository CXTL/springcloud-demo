package com.dupake.finance.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dupake.common.pojo.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 财务-投资人信息表
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
public class FinInvestor  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 投资人名称
     */
    private String investorName;

    /**
     * 投资人电话
     */
    private String investorPhone;

    /**
     * 投资人地址
     */
    private String investorAddress;


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
