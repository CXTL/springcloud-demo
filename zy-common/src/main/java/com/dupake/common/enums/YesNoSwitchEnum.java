package com.dupake.common.enums;

/**
 * 是否开关状态
 */
public enum YesNoSwitchEnum {
    NO(0, "否"),
    YES(1, "是"),

    ;

    private Integer value;

    private String desc;

    YesNoSwitchEnum(Integer value, String desc) {
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

    public static YesNoSwitchEnum getEnumValue(Integer value) {
        switch (value) {
            case 1:
                return YES;
            case 0:
                return NO;
            default:
                return YES;
        }
    }

    public static boolean isExist(Integer value) {
        for (YesNoSwitchEnum itm : YesNoSwitchEnum.values()) {
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
