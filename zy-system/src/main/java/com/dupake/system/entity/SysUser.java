package com.dupake.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 0:未激活1:已激活2:已锁定3:已注销4::账号异常
     */
    private Integer status;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 创建人
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Long updateId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
     * 是否删除 0:未删除1:已删除
     */
    private Integer isDeleted;

}
