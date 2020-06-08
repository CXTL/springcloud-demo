package com.dupake.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户角色关联表 
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SysUserRole implements Serializable {

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
     * 角色ID
     */
    private Long roleId;

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0:未删除1:已删除
     */
    private String isDeleted;

}
