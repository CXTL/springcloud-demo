package com.dupake.commodity.mapper;

import com.dupake.commodity.entity.Point;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 17:00
 * @description
 */

public interface PointMapper {

    int getByOrderNo(@Param("orderNo") String orderNo);

    void insert(Point points);
}
