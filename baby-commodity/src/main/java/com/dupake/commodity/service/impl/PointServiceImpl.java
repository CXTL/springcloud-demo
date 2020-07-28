package com.dupake.commodity.service.impl;

import com.dupake.commodity.entity.Point;
import com.dupake.commodity.mapper.PointMapper;
import com.dupake.commodity.service.PointService;
import com.dupake.common.pojo.dto.res.OrderDTO;
import com.dupake.common.utils.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 15:30
 * @description
 */
@Service
public class PointServiceImpl implements PointService {

    @Resource
    PointMapper pointsMapper;

    IdUtil idUtil = new IdUtil(1, 1, 1);
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void increasePoints(OrderDTO order) {

        //入库之前先查询，实现幂等
        int count = pointsMapper.getByOrderNo(order.getOrderNo());
        if (count > 0) {
            logger.info("积分添加完成，订单已处理。{}", order.getOrderNo());
        } else {
            Point points = new Point();
//            points.setId(idUtil.nextId());
            points.setUserId(1L);
            points.setOrderNo(order.getOrderNo());
            points.setPoints(order.getAmount());
            points.setRemarks("商品消费共【" + order.getAmount() + "】元，获得积分" + points.getPoints());
            points.setCreateTime(System.currentTimeMillis());
            points.setUpdateTime(System.currentTimeMillis());
            pointsMapper.insert(points);
            logger.info("已为订单号码{}增加积分:{}。", points.getOrderNo(), points.getPoints());
        }
    }
}
