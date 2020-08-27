package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.InvestReportDTO;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/27 上午10:32
 */
public interface InvestReportMapper {

    List<InvestReportDTO> getReportInvestAsset(AssetReportQueryRequest reportQueryRequest);
}
