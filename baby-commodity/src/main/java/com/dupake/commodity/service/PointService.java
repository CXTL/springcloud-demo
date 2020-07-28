package com.dupake.commodity.service;

import com.dupake.common.pojo.dto.res.finance.OrderDTO;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:58
 * @description
 */
public interface PointService {


    void increasePoints(OrderDTO order);
}
