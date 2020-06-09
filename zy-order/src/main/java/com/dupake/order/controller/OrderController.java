package com.dupake.order.controller;

import com.dupake.order.entity.Order;
import com.dupake.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:53
 * @description
 */
@RestController
@Api(tags = "订单控制中心")
@RequestMapping(value = "authUser")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "查询订单信息", notes = "查询订单信息 - for-web")
    @GetMapping(value = "/findInfoById")
    public Result findInfoById(@RequestParam(value = "id") Integer id) {
        Order authUser = orderService.findInfoById(id);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表 - for-web")
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Order> list = orderService.findList(pageNum, pageSize);
        return Result.ok(list);
    }

    @ApiOperation(value = "新增订单", notes = "新增订单 - for-web")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody Order user) {
        Order authUser = orderService.insert(user);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "修改订单", notes = "修改订单 - for-web")
    @PostMapping(value = "/update")
    public Result update(@RequestBody Order user) {
        Order authUser = orderService.update(user);
        return Result.ok(authUser);
    }

    @ApiOperation(value = "删除订单", notes = "删除订单 - for-web")
    @PostMapping(value = "/delete")
    public Result deleteByIds(@RequestParam Integer id) {
        orderService.deleteByIds(id);
        return Result.ok();
    }

}
