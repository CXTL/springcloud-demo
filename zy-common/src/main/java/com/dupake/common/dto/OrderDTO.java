package com.dupake.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 14:52
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderNo;
    private BigDecimal amount;
    private Integer status;
    private String commodityCode;
}
