/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.tools.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author dupake
 * @date 2018-11-23
 * 统一异常处理
 */
@Getter
public class BadRequestException extends RuntimeException{

    private Integer status = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
