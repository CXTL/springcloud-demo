package com.dupake.common.pojo.dto.req.account;

import lombok.Data;

/**
 * @ClassName AccountAddRequest
 * @Description 新增菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class AccountAddRequest {


    /**
     * 帐套信息编码
     */
    private String accountCode;

    /**
     * 帐套名称
     */
    private String accountName;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 纳税识别号
     */
    private String taxNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 开户银行
     */
    private String bankAccount;

    /**
     * 银行卡号
     */
    private String bankCardNumber;


    /**
     * 备注
     */
    private String remark;
}
