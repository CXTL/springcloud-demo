package com.dupake.common.message;

import lombok.Getter;

@Getter
public enum BaseResult implements IErrorCode {
    SUCCESS(200, "操作成功！"),
    FAILED(500, "操作失败！"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    /***************************************系统模块枚举***************************************/
    SYS_USER_IS_NOT_EXIST(10001, "用户不存在"),
    SYS_USERNAME_IS_EXIST(10002, "账号已存在"),
    SYS_EMAIL_IS_EXIST(10003, "邮箱已存在"),
    SYS_PHONE_IS_EXIST(10004, "手机号已存在"),
    SYS_USERNAME_PASSWORD_ERROR(10005, "账号或密码错误"),
    SYS_USERNAME_ERROR(10005, "账号异常"),

    /***************************************订单模块枚举***************************************/

    /***************************************统计模块枚举***************************************/
    ;

    private final Integer code;
    private final String message;

    BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
