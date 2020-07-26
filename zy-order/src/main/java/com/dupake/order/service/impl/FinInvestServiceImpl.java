package com.dupake.order.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.invest.InvestAddRequest;
import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.common.pojo.dto.req.invest.InvestUpdateRequest;
import com.dupake.common.pojo.dto.res.InvestDTO;
import com.dupake.order.service.FinInvestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务-投资信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class FinInvestServiceImpl implements FinInvestService {

    @Override
    public CommonResult<CommonPage<InvestDTO>> listByPage(InvestQueryRequest accountQueryRequest) {
        return null;
    }

    @Override
    public CommonResult addInvest(InvestAddRequest accountAddRequest) {
        return null;
    }

    @Override
    public CommonResult updateInvest(InvestUpdateRequest accountUpdateRequest) {
        return null;
    }

    @Override
    public CommonResult deleteInvest(List<Long> ids) {
        return null;
    }
}
