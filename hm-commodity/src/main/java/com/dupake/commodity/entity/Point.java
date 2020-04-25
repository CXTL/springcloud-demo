package com.dupake.commodity.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 15:26
 * @description 积分实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Point implements Serializable {
    private static final long serialVersionUID = -6417252793902248382L;
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal points;
    private Long createTime;
    private Long updateTime;
    private String remarks;
}
