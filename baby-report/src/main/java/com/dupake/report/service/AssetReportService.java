package com.dupake.report.service;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.AssetInfoDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:21
 */
public interface AssetReportService {

    CommonResult<List<HomeReportAssetDTO>> getAssetChartData(AssetReportQueryRequest reportQueryRequest);

    CommonResult<List<AssetInfoDTO>> getAssetInfoData(AssetReportQueryRequest reportQueryRequest);


}
