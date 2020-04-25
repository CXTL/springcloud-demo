package com.dupake.common.message;

import lombok.Getter;

@Getter
public enum UserResult implements IRet {
    ERROR_USER_NOT_FIND("1001", "用户不存在！");

    private final String code;
    private final String message;

    UserResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
