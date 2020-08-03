/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package com.dupake.system.exception;

import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonResult;
import com.dupake.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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


    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return CommonResult.failed(BaseResult.VALIDATE_FAILED.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        return CommonResult.failed(BaseResult.VALIDATE_FAILED.getCode(), "参数体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return CommonResult.failed(BaseResult.VALIDATE_FAILED.getCode(), fieldError.getDefaultMessage());
            }
        }
        return CommonResult.failed();
    }

}
