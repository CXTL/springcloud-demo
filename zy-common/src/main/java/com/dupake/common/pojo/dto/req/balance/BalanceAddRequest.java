package com.dupake.common.pojo.dto.req.balance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AccountAddRequest
 * @Description 新增菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class BalanceAddRequest {


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
     * 备注
     */
    private String remark;
}