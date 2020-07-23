package com.dupake.common.pojo.dto.req.menu;

import com.dupake.common.pojo.dto.req.BasePageRequest;
import lombok.Data;

/**
 * @ClassName UserQueryRequest
 * @Description 用户查询请求实体
 * @Author dupake
 * @Date 2020/6/9 10:57
 */
@Data
public class MenuQueryRequest extends BasePageRequest {

    private static final long serialVersionUID = -1970997896879063951L;
    
    private String name;

    private String phone;

    private String email;
}
