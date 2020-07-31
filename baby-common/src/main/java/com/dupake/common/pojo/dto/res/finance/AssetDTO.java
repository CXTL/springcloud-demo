package com.dupake.common.pojo.dto.res.finance;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName PermissionDTO
 * @Description 权限DTO
 * @Author dupake
 * @Date 2020/6/9 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetDTO implements Serializable {

    private static final long serialVersionUID = -6225606748663424690L;
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
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;

}
