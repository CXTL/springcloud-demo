package com.dupake.finance.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.invest.InvestAddRequest;
import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.common.pojo.dto.req.invest.InvestUpdateRequest;
import com.dupake.common.pojo.dto.res.InvestDTO;

import java.util.List;

/**
 * <p>
 * 财务-投资信息表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinInvestService {

    CommonResult<CommonPage<InvestDTO>> listByPage(InvestQueryRequest accountQueryRequest);

    CommonResult addInvest(InvestAddRequest accountAddRequest);

    CommonResult updateInvest(InvestUpdateRequest accountUpdateRequest);

    CommonResult deleteInvest(List<Long> ids);
}
