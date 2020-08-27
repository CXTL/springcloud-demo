package com.dupake.report.service.impl;

import com.dupake.common.constatnts.NumberConstant;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.InOutDTO;
import com.dupake.common.pojo.dto.res.report.InvestReportDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.report.mapper.InvestReportMapper;
import com.dupake.report.service.InvestReportService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/27 上午10:28
 */
@Service
public class InvestReportServiceImpl implements InvestReportService {

    @Resource
    private InvestReportMapper investReportMapper;

    @Override
    public CommonResult<List<InvestReportDTO>> getInvestChartData(AssetReportQueryRequest reportQueryRequest) {

        //查询投资记录
        Long endByTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(DateUtil.convertLongToLDT(reportQueryRequest.getEndTime())));
        reportQueryRequest.setEndTime(endByTime);
        List<InvestReportDTO> targetReportAssetList = investReportMapper.getReportInvestAsset(reportQueryRequest);

        return CommonResult.success(targetReportAssetList);
    }

    @Override
    public CommonResult<CommonPage<InvestReportDTO>> listInvestInfoByPage(AssetReportQueryRequest reportQueryRequest) {
        return null;
    }

    @Override
    public void exportInvest(AssetReportQueryRequest reportQueryRequest, HttpServletResponse response) {

    }
}
