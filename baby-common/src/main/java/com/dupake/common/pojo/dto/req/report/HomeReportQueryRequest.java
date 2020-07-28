package com.dupake.common.pojo.dto.req.report;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName HomeReportQueryRequest
 * @Description 首页报表请求实体
 * @Author dupake
 * @Date 2020/6/9 10:57
 */
@Data
public class HomeReportQueryRequest implements Serializable {

    private static final long serialVersionUID = 1162642735857389767L;

    private String accountCode;

    private Long startTime;

    private Long endTime;

}
