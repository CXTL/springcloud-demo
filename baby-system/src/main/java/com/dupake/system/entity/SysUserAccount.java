package com.dupake.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户帐套关联表 
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SysUserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 帐套编号
     */
    private String accountCode;

}
