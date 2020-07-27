package com.dupake.finance.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.balance.BalanceAddRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceQueryRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceUpdateRequest;
import com.dupake.common.pojo.dto.res.BalanceDTO;

import java.util.List;

/**
 * <p>
 * 财务-余额信息表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinBalanceService  {

    CommonResult<CommonPage<BalanceDTO>> listByPage(BalanceQueryRequest balanceQueryRequest);

    CommonResult addBalance(BalanceAddRequest balanceAddRequest);

    CommonResult updateBalance(BalanceUpdateRequest balanceUpdateRequest);

    CommonResult deleteBalance(List<Long> ids);
}
