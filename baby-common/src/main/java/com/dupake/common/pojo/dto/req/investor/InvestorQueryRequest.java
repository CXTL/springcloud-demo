package com.dupake.common.pojo.dto.req.investor;

import com.dupake.common.pojo.dto.req.BasePageRequest;
import lombok.Data;

/**
 * @ClassName AccountQueryRequest
 * @Description 投资人请求实体
 * @Author dupake
 * @Date 2020/6/9 10:57
 */
@Data
public class InvestorQueryRequest extends BasePageRequest {

    private static final long serialVersionUID = -1970997896879063951L;
    
    private String investorName;

    private Long startTime;

    private Long endTime;
}
