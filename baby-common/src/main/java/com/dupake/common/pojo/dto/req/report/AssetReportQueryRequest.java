package com.dupake.common.pojo.dto.req.report;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:27
 */
@Data
public class AssetReportQueryRequest  implements Serializable {
    private static final long serialVersionUID = -1225268187626076765L;

    private String accountCode;

    private String subjectCode;

    /**
     * 类型 1 收入 2 支出
     */
    private Integer type;

    private Long startTime;

    private Long endTime;

}
