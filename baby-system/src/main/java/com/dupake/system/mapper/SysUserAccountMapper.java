package com.dupake.system.mapper;

import com.dupake.system.entity.SysUserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户帐套关联表  Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Mapper
public interface SysUserAccountMapper extends BaseMapper<SysUserAccount> {

    void insertBatch(List<SysUserAccount> sysUserAccounts);
}
