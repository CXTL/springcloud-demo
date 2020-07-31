package com.dupake.common.pojo.dto.req.asset;

import com.dupake.common.pojo.dto.req.BasePageRequest;
import lombok.Data;

/**
 * @ClassName AccountQueryRequest
 * @Description 菜单查询请求实体
 * @Author dupake
 * @Date 2020/6/9 10:57
 */
@Data
public class AssetQueryRequest extends BasePageRequest {

    private static final long serialVersionUID = -1970997896879063951L;
    
    private String accountCode;

    private Long startTime;

    private Long endTime;
}
