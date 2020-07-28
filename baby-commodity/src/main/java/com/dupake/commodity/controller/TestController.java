package com.dupake.commodity.controller;

import com.dupake.commodity.feign.HelloService;
import com.dupake.common.message.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 9:40
 * @description
 */
@RestController
@Api(tags = "Test控制器")
@RequestMapping(value = "test")
public class TestController {


    @Resource
    private HelloService helloService;


    @ApiOperation(value = "测试feign/sentinel", notes = "测试feign和融断 - for-web")
    @GetMapping("/hello-feign/{str}")
    public CommonResult feign(@PathVariable String str)  {
        String hello = helloService.hello(str);
        return CommonResult.success(hello);
    }


}
