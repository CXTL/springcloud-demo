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



    /**任何人都能访问
     * @return
     */
    @ApiOperation(value = "权限测试", notes = "权限测试 - for-web")
    @GetMapping("/publicMsg")
    public String getMsg(){
        return "you get the message!";
    }

    /**登录的用户才能访问
     * @return
     */
    @GetMapping("/innerMsg")
    public String innerMsg(){
        return "you get the message!";
    }

    /**管理员(admin)才能访问
     * @return
     */
    @GetMapping("/secret")
    public String secret(){
        return "you get the message!";
    }



}
