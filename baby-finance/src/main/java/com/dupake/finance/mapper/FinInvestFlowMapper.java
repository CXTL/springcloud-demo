package com.dupake.finance.mapper;

import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.finance.entity.FinInvestFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 财务-投资信息表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinInvestFlowMapper extends BaseMapper<FinInvestFlow> {

    int getTotalCount(InvestQueryRequest investQueryRequest);

    List<FinInvestFlow> selectListPage(InvestQueryRequest investQueryRequest);

    void updateBatch(List<FinInvestFlow> finInvestFlowList);

    FinInvestFlow selectInfoByAccountCode(@Param("accountCode") String accountCode, @Param("investorId") Long investorId);
}
