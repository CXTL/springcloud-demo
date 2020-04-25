package com.dupake.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallBack {

    @GetMapping("/fallback")
    public Map<String, Object> fallback() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Code", 100);
        map.put("Message", "服务暂时不可用");
        return map;
    }

}