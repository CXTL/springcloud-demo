package com.dupake.finance.controller;

import com.dupake.common.message.CommonResult;
import com.dupake.finance.entity.Order;
import com.dupake.finance.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
    private OrderService orderService;


    @ApiOperation(value = "查询订单信息", notes = "查询订单信息 - for-web")
    @GetMapping(value = "/findInfoById")
    public CommonResult findInfoById(@RequestParam(value = "id") Integer id) {
        Order authUser = orderService.findInfoById(id);
        return CommonResult.success(authUser);
    }

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表 - for-web")
    @GetMapping(value = "/findList")
    public CommonResult findList(@RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Order> list = orderService.findList(pageNum, pageSize);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "新增订单", notes = "新增订单 - for-web")
    @PostMapping(value = "/insert")
    public CommonResult insert(@RequestBody Order user) {
        Order authUser = orderService.insert(user);
        return CommonResult.success(authUser);
    }

    @ApiOperation(value = "修改订单", notes = "修改订单 - for-web")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody Order user) {
        Order authUser = orderService.update(user);
        return CommonResult.success(authUser);
    }

    @ApiOperation(value = "删除订单", notes = "删除订单 - for-web")
    @PostMapping(value = "/delete")
    public CommonResult deleteByIds(@RequestParam Integer id) {
        orderService.deleteByIds(id);
        return CommonResult.success();
    }

}
