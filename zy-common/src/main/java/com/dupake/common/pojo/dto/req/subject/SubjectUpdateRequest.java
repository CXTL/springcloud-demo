package com.dupake.common.pojo.dto.req.subject;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AccountUpdateRequest
 * @Description 修改 菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class SubjectUpdateRequest implements Serializable {

    private static final long serialVersionUID = 4371186875731167316L;

    private Long id;

    /**
     * 科目编号
     */
    private String subjectCode;

    /**
     * 科目名称
     */
    private String companyName;

    /**
     * 父科目编号
     */
    private String taxNumber;

    /**
     * 科目类型 1 资产 2负载 3权益 4成本 5其他
     */
    private Integer subjectType;

    /**
     * 借贷方向 0:借 1:贷
     */
    private Integer borrowFlag;

    /**
     * 备注
     */
    private String remark;
}
