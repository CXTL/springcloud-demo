package com.dupake.common.pojo.dto.req.investor;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AccountAddRequest
 * @Description 新增菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class InvestorAddRequest {



    /**
     * 投资人名称
     */
    private String investorName;

    /**
     * 投资人电话
     */
    private String investorPhone;

    /**
     * 投资人地址
     */
    private String investorAddress;


    /**
     * 备注
     */
    private String remark;
}
