package com.dupake.order.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.account.AccountAddRequest;
import com.dupake.common.pojo.dto.req.account.AccountQueryRequest;
import com.dupake.common.pojo.dto.req.account.AccountUpdateRequest;
import com.dupake.common.pojo.dto.res.AccountDTO;
import com.dupake.order.service.FinAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务-帐套信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class FinAccountServiceImpl implements FinAccountService {

    @Override
    public CommonResult<CommonPage<AccountDTO>> listByPage(AccountQueryRequest accountQueryRequest) {
        return null;
    }

    @Override
    public CommonResult addAccount(AccountAddRequest accountAddRequest) {
        return null;
    }

    @Override
    public CommonResult updateAccount(AccountUpdateRequest accountUpdateRequest) {
        return null;
    }

    @Override
    public CommonResult deleteAccount(List<Long> ids) {
        return null;
    }
}
