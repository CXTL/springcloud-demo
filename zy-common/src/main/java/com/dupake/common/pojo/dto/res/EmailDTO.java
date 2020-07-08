package com.dupake.common.pojo.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName EmailDTO
 * @Description 邮件DTO
 * @Author
 * @Date 2020/6/15 14:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    /**
     * 邮箱地址
     */
    String receiverMailAddress;
    /**
     * 标题
     */
    String subject;
    /**
     * 验证码
     */
    String code;
    /**
     * 类型  1 用户注册 2 修改邮箱 3 其他
     */
    Integer type;

}
