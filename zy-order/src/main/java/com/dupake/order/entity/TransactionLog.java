package com.dupake.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 14:26
 * @description 事务日志记录实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLog {
    private String id;
    private String business;
    private String foreignKey;
    private Long createTime;
    private Long updateTime;
}
