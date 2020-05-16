package com.dupake.common.message;

import lombok.Getter;

@Getter
public enum BaseResult implements IRet {
    SUCCESS("10010", "操作成功！"),
    ERROR("10011", "操作失败！");

    private final String code;
    private final String message;

    BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
