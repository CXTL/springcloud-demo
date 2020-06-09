package com.dupake.common.enums;

/**
 * 用户状态枚举
 */
public enum UserStatusEnum {
    INACTIVATED(0, "未激活"),
    ACTIVATED(1, "已激活"),
    LOCKED(2, "已锁定"),
    CANCELLED(3, "已注销"),
    ACCOUNT_ABNORMAL(4, "账号异常"),

    ;

    private Integer value;

    private String desc;

    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static UserStatusEnum getEnumValue(Integer value) {
        switch (value) {
            case 0:
                return INACTIVATED;
            case 1:
                return ACTIVATED;
            case 2:
                return LOCKED;
            case 3:
                return CANCELLED;
            case 4:
                return ACCOUNT_ABNORMAL;
            default:
                return INACTIVATED;
        }
    }

    public static boolean isExist(Integer value) {
        for (UserStatusEnum itm : UserStatusEnum.values()) {
            if (value == itm.getValue()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "[" + this.value + "]" + this.desc;
    }

}
