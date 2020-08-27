package com.dupake.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.common.pojo.dto.req.investor.InvestorQueryRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorQueryRequest;
import com.dupake.finance.entity.FinInvestor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 财务-投资人信息表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Mapper
public interface FinInvestorMapper extends BaseMapper<FinInvestor> {

    int getTotalCount(InvestorQueryRequest investorQueryRequest);

    List<FinInvestor> selectListPage(InvestorQueryRequest investorQueryRequest);

    void updateBatch(List<FinInvestor> finInvestorList);
}
