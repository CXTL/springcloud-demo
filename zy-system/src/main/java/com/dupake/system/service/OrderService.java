package com.dupake.system.service;

import com.dupake.common.dto.OrderDTO;
import com.dupake.system.entity.Order;
import com.github.pagehelper.PageInfo;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:58
 * @description
 */
public interface OrderService {

    Order findInfoById(Integer id);

    PageInfo<Order> findList(Integer pageNum, Integer pageSize);

    Order insert(Order user);

    Order update(Order user);

    void deleteByIds(Integer ids);

    void createOrder(OrderDTO orderDTO, String transactionId);

}
