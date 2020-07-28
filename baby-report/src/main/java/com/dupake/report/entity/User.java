package com.dupake.report.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * User
 *
 * @author star
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Excel(name = "ID",width = 20D)
    private Integer id;

    @Excel(name = "名称", orderNum = "1",width = 20D)
    private String name;

    @Excel(name = "邮箱", orderNum = "2", width = 30D)
    private String email;

    @Excel(name = "年龄", orderNum = "3", width = 20D)
    private Integer age;

    @Excel(name = "创建时间", orderNum = "4", format = "yyyy-MM-dd HH:mm:ss",width = 40D)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
