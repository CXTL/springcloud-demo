/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package com.dupake.finance.exception;

import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonResult;
import com.dupake.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author dupake
 * @date 2018-11-23
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings("unchecked")
public class GlobalExceptionHandler {



    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public CommonResult handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return CommonResult.failed(BaseResult.SYSTEM_BUSY.getCode(), BaseResult.SYSTEM_BUSY.getMessage());
    }



    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BadRequestException.class)
    public CommonResult badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return CommonResult.failed(e.getCode(), e.getMessage());
    }


}
