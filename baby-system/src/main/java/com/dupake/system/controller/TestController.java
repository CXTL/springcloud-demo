package com.dupake.system.controller;

import com.dupake.common.annotation.SubmitToken;
import com.dupake.common.message.CommonResult;
import com.dupake.system.feign.HelloService;
import com.dupake.system.utils.CacheUtil;
import com.dupake.system.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取唯一ID
     *
     * @return
     */
    @ApiOperation(value = "获取唯一ID", notes = "获取唯一ID - for-web")
    @GetMapping("/getSubmitToken")
    public CommonResult getSubmitToken() {
        String submitToken = UUIDUtil.genId();
        //将事务请求唯一ID放入缓存池
        CacheUtil.addCache(submitToken, "false");
        return CommonResult.success(submitToken);
    }

    @SubmitToken
    @ApiOperation(value = "测试防重复提交", notes = "测试防重复提交 - for-web")
    @GetMapping("/testSubmit")
    public CommonResult testSubmit() {
        System.out.println("test");
        return CommonResult.success();
    }

    @ApiOperation(value = "测试feign/sentinel", notes = "测试feign和融断 - for-web")
    @GetMapping("/hello-feign/{str}")
    public CommonResult feign(@PathVariable String str)  {
        String hello = helloService.hello(str);
        return CommonResult.success(hello);
    }


}
