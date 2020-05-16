package com.dupake.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 9:40
 * @description
 */
@RestController
@Api(tags = "Test控制器")
public class TestController {



    @ApiOperation(value = "redis测试", notes = "redis测试 - for-web")
    @GetMapping("/testRedis")
    public void testRedis() {

    }


}
