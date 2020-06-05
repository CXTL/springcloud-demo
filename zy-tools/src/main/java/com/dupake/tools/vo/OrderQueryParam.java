/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.tools.vo;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public abstract class OrderQueryParam extends QueryParam{
    private static final long serialVersionUID = 57714391204790143L;

    private List<OrderItem> orders;

    public void defaultOrder(OrderItem orderItem){
        this.defaultOrders(Arrays.asList(orderItem));
    }

    public void defaultOrders(List<OrderItem> orderItems){
        if (CollectionUtils.isEmpty(orderItems)){
            return;
        }
        this.orders = orderItems;
    }

}
