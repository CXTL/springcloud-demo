package com.dupake.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/17 10:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;

}
