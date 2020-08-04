package com.dupake.report.service;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;

import java.util.List;

/**
 * @Description 首页报表
 * @Authror xt
 * @Date 2020/7/28 下午2:05
 */
public interface HomeReportService {

    CommonResult<HomeTableDTO> getHomeTableData(HomeReportQueryRequest reportQueryRequest);

    CommonResult<List<HomeReportAssetDTO>> getHomeChartData(HomeReportQueryRequest reportQueryRequest);
}
