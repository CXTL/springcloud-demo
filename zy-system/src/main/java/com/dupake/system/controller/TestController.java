package com.dupake.system.controller;

import com.dupake.common.annotation.SubmitToken;
import com.dupake.common.message.CommonResult;
import com.dupake.system.utils.CacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    /**
     * 获取唯一ID
     *
     * @return
     */
    @ApiOperation(value = "获取唯一ID", notes = "获取唯一ID - for-web")
    @GetMapping("/getSubmitToken")
    public CommonResult getSubmitToken() {
        String submitToken = UUID.randomUUID().toString();
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


}
