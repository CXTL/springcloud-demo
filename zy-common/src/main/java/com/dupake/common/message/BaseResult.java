package com.dupake.common.message;

import lombok.Getter;

@Getter
public enum BaseResult implements IRet {
    SUCCESS("200", "操作成功！"),
    ERROR("500", "操作失败！"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "暂未登录或token已经过期"),
    FORBIDDEN("403", "没有相关权限");

    private final String code;
    private final String message;

    BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
