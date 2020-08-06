package com.dupake.finance.mapper;

import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.finance.entity.FinInvest;
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
public interface FinInvestMapper extends BaseMapper<FinInvest> {

    int getTotalCount(InvestQueryRequest investQueryRequest);

    List<FinInvest> selectListPage(InvestQueryRequest investQueryRequest);

    void updateBatch(List<FinInvest> finInvestList);

    FinInvest selectInfoByAccountCode(@Param("accountCode") String accountCode, @Param("investName") String investName);
}
