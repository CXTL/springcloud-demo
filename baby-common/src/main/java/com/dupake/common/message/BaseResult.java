package com.dupake.common.message;

import lombok.Getter;

@Getter
public enum BaseResult implements IErrorCode {
    SUCCESS(200, "操作成功！"),
    SYSTEM_BUSY(201, "系统繁忙！"),
    FAILED(500, "操作失败！"),
    VALIDATE_FAILED(405, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    /***************************************系统模块枚举***************************************/
    SYS_USER_IS_NOT_EXIST(10001, "用户不存在"),
    SYS_USERNAME_IS_EXIST(10002, "账号已存在"),
    SYS_EMAIL_IS_EXIST(10003, "邮箱已存在"),
    SYS_PHONE_IS_EXIST(10004, "手机号已存在"),
    SYS_USERNAME_PASSWORD_ERROR(10005, "账号或密码错误"),
    SYS_USERNAME_ERROR(10005, "账号异常"),
    SYS_USER_CANNOT_DELETE(10006, "系统默认用户不可删除!"),

    SYS_MENU_NAME_IS_EXIST(10007, "菜单名称已存在!"),
    SYS_MENU_PERMISSION_IS_EXIST(10008, "权限标识已存在!"),
    SYS_MENU_DELETE_ERROR_EXIST_SUB_MENU(10009, "该菜单存在子菜单,无法删除!"),
    SYS_ROLE_NAME_IS_EXIST(10010, "角色名称已存在!"),
    SYS_ROLE_INFO_IS_NOT_EXIST(10011, "角色信息不存在!"),
    SYS_MENU_INFO_IS_NOT_EXIST(10012, "角色信息不存在!"),

    /***************************************财务模块枚举***************************************/
    FIN_SUBJECT_DELETE_ERROR_EXIST_SUB_SUBJECT(10010, "该科目存在子科目,无法删除!"),
    /***************************************统计模块枚举***************************************/
    ;

    private final Integer code;
    private final String message;

    BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
