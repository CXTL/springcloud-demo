package com.dupake.common.pojo.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/17 10:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

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
     * 创建时间
     */
    private Long createTime;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 备注
     */
    private String remark;

    /**
     * 用户所属角色
     */
    private List<RoleDTO> roleList;

}
