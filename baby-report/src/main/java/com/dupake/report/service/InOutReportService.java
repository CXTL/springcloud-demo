package com.dupake.report.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.InOutDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:21
 */
public interface InOutReportService {

    CommonResult<List<InOutDTO>> getInOutChartData(AssetReportQueryRequest reportQueryRequest);

    CommonResult<CommonPage<InOutDTO>> listInOutAssetInfoByPage(AssetReportQueryRequest reportQueryRequest);


    void exportAsset(AssetReportQueryRequest reportQueryRequest, HttpServletResponse response);
}
