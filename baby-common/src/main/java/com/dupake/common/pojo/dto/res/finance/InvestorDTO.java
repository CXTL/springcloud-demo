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
 * @ClassName InvestorDTO
 * @Description 投资人DTO
 * @Author dupake
 * @Date 2020/6/9 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestorDTO implements Serializable {

    private static final long serialVersionUID = -6225606748663424690L;
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
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;


}
