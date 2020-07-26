package com.dupake.order.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.balance.BalanceAddRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceQueryRequest;
import com.dupake.common.pojo.dto.req.balance.BalanceUpdateRequest;
import com.dupake.common.pojo.dto.res.BalanceDTO;
import com.dupake.order.service.FinBalanceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务-余额信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class FinBalanceServiceImpl  implements FinBalanceService {

    @Override
    public CommonResult<CommonPage<BalanceDTO>> listByPage(BalanceQueryRequest balanceQueryRequest) {
        return null;
    }

    @Override
    public CommonResult addBalance(BalanceAddRequest balanceAddRequest) {
        return null;
    }

    @Override
    public CommonResult updateBalance(BalanceUpdateRequest balanceUpdateRequest) {
        return null;
    }

    @Override
    public CommonResult deleteBalance(List<Long> ids) {
        return null;
    }
}
