package com.dupake.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午6:50
 */
@RestController
//@RequestMapping("/provider")
public class TestController {


    @GetMapping("/helloProvider")
    public String helloProvider(){
        return "hello report______________________";
    }

}
