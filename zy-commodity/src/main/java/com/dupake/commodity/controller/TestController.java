package com.dupake.commodity.controller;

import com.dupake.commodity.feign.OrderFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/16 16:53
 * @description
 */
@RestController
@Api(tags = "Test控制器")
@RequestMapping(value = "test")
public class TestController {

    @Resource
    private OrderFeignService orderFeignService;

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息 - for-web")
    @GetMapping(value = "/hello")
    public Result hello() {
        return orderFeignService.hello();
    }


}
