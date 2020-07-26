package com.dupake.common.pojo.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PermissionDTO
 * @Description 权限DTO
 * @Author dupake
 * @Date 2020/6/9 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO implements Serializable {

    private static final long serialVersionUID = -6225606748663424690L;
    private Long id;

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
     * 是否删除 0:未删除1:已删除
     */
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long createTime;

}
