package com.dupake.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName SysRole.java
 * @Description TODO
 * @createTime 2020年05月17日 23:03:00
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysRole {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;
}
