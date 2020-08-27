package com.dupake.finance.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.investor.InvestorAddRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorQueryRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.InvestorDTO;

import java.util.List;

/**
 * <p>
 * 财务-帐套信息表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinInvestorService {

    CommonResult<CommonPage<InvestorDTO>> listByPage(InvestorQueryRequest accountQueryRequest);

    CommonResult addInvestor(InvestorAddRequest accountAddRequest);

    CommonResult updateInvestor(InvestorUpdateRequest accountUpdateRequest);

    CommonResult deleteInvestor(List<Long> ids);

}
