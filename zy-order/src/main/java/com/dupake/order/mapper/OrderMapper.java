package com.dupake.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 17:00
 * @description
 */

public interface OrderMapper extends BaseMapper<Order> {
    Order findInfoById(Integer id);

    List<Order> findList();

    void insertOrder(Order user);

    void update(Order user);

    void delete(Integer id);
}
