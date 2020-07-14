package com.dupake.search.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName Response.java
 * @Description TODO
 * @createTime 2019年09月12日 10:17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;
    private String message;
    private Object data;


    public static Response successMsg(){
        Response response=new Response();
        response.setCode(200);
        response.setMessage("请求成功");
        return response;
    }

    public static Response failed404(){
        Response response=new Response();
        response.setCode(404);
        response.setMessage("请求失败");
        return response;
    }
}
