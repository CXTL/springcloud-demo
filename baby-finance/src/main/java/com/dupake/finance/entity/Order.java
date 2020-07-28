package com.dupake.finance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:48
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private static final long serialVersionUID = 3836141340701366647L;
    private Long id;
    private String orderNo;
    private String name;
    private Long createTime;
    private Long updateTime;
    private BigDecimal amount;
    private Integer status;
    private String commodityCode;
}
