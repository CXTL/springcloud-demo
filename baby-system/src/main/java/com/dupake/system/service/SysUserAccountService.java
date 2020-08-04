package com.dupake.system.service;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.common.pojo.dto.res.system.UserAccountDTO;

import java.util.List;

/**
 * <p>
 * 用户帐套关联表  服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface SysUserAccountService  {

    CommonResult allocAccount(Long userId, List<String> accountCodes);

    CommonResult<List<AccountDTO>> listAll();

    CommonResult<List<UserAccountDTO>> getAccountByAdmin(Long userId);
}
