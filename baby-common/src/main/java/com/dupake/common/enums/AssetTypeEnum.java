package com.dupake.common.enums;

/**
 * 资产类型枚举
 */
public enum AssetTypeEnum {
    INCOME(1, "收入"),
    EXPENDITURE(2, "支出"),
    INVEST(3, "投资"),
    NO_KNOW(0, "其他"),

    ;

    private Integer value;

    private String desc;

    AssetTypeEnum(Integer value, String desc) {
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

    public static AssetTypeEnum getEnumValue(Integer value) {
        switch (value) {
            case 1:
                return INCOME;
            case 2:
                return EXPENDITURE;
            case 3:
                return INVEST;
            default:
                return NO_KNOW;
        }
    }

    public static boolean isExist(Integer value) {
        for (AssetTypeEnum itm : AssetTypeEnum.values()) {
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
