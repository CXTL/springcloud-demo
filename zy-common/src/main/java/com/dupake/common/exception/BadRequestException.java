/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package com.dupake.common.exception;

import com.dupake.common.message.BaseResult;

/**
 * @author dupake
 * @date 2018-11-23
 * 自定义 异常处理
 */
public class BadRequestException extends RuntimeException {

    //自定义错误码
    private Integer code;

    //自定义构造器，只保留一个，让其必须输入错误码及内容
    public BadRequestException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BadRequestException(String msg) {
        super(msg);
        this.code = BaseResult.FAILED.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
