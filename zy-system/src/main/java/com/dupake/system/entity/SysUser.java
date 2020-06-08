package com.dupake.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysUser.java
 * @Description TODO
 * @createTime 2020年05月17日 23:03:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    private String username;

    // 用户状态，0-封禁，1-正常
    private Integer state;

    @JsonIgnore
    private List<SysRole> roles;
}
