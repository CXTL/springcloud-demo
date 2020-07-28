package com.dupake.common.pojo.dto.req.subject;

import lombok.Data;

/**
 * @ClassName AccountAddRequest
 * @Description 新增菜单 请求实体
 * @Author dupake
 * @Date 2020/6/9 9:59
 */
@Data
public class SubjectAddRequest {



    /**
     * 科目编号
     */
    private String subjectCode;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 父科目编号
     */
    private String parentCode;

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
