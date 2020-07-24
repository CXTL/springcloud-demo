package com.dupake.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dupake.common.pojo.entity.BaseEntity;
import lombok.*;

/**
 * <p>
 * 角色表
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
public class SysRole extends BaseEntity  {


    private static final long serialVersionUID = -6273784338638022278L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否启用 1:是 0:否
     */
    private Integer isEnable;

    /**
     * 备注
     */
    private String remark;


    /**
     * 是否删除 0:未删除1:已删除
     */
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

}
