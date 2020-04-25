package com.dupake.order.service.impl;

import com.dupake.order.service.IdGeneratorService;
import com.dupake.order.utils.RedisUtil;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 17:43
 * @description 基于Redis分布式ID生成实现
 */
@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

    @Autowired
    private RedisUtil redisUtil;


    /**
     * @param date
     * @return
     * @Description
     * @author xt
     */
    private String getOrderPrefix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_YEAR); // 今天是第多少天
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String dayFmt = String.format("%1$03d", day); // 0补位操作 必须满足三位
        String hourFmt = String.format("%1$02d", hour);  // 0补位操作 必须满足2位
        StringBuffer prefix = new StringBuffer();
        prefix.append((year)).append(dayFmt).append(hourFmt);
        return prefix.toString();
    }


    /**
     * @param prefix
     * @return
     * @Description 支持一个小时100w个订单号的生成
     * @author xt
     */
    private Long incrOrderId(String biz, String prefix) {
        String orderId = null;
        String key = "geese:#{biz}:id:".replace("#{biz}", biz).concat(prefix); // 00001
        try {
            Long index = redisUtil.incr(key, 1L);
            orderId = prefix.concat(String.format("%1$05d", index)); // 补位操作 保证满足5位
        } catch (Exception ex) {
            System.out.println("分布式订单号生成失败异常。。。。。");
        }
        if (Strings.isNullOrEmpty(orderId)) return null;
        return Long.parseLong(orderId);
    }

    /**
     * @return
     * @Description 生成分布式ID
     * @author xt
     */

    @Override
    public Long generatorId(String biz) {
        // 转成数字类型，可排序
        return incrOrderId(biz, getOrderPrefix(new Date()));
    }
}
