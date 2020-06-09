package com.dupake.common.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginVO
 * @Description 登录请求实体
 * @Author dupake
 * @Date 2020/5/25 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
