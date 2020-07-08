package com.dupake.common.pojo.dto.req.user;

import lombok.Data;

/**
 * @ClassName UserQueryReuqest
 * @Description 用户查询请求实体
 * @Author dupake
 * @Date 2020/6/9 10:57
 */
@Data
public class UserQueryRequest {

    private String username;

    private String phone;

    private String email;
}
