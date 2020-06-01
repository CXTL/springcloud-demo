package com.dupake.system.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Result
 * @Description TODO
 * @Author dupake
 * @Date 2020/5/25 11:20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
public class Result {

    private Integer code; // 返回状态码

    private String message; // 返回信息

    private Object data; // 返回数据

    private Result(){

    }

    public Result(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result create(Integer code, String message){
        return new Result(code,message);
    }

    public static Result create(Integer code, String message, Object data){
        return new Result(code,message,data);
    }
}