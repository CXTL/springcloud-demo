package com.dupake.report.controller;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.InOutDTO;
import com.dupake.report.service.InOutReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午2:02
 */
@Api(tags = "报表：资产信息")
@RestController
@RequestMapping("/report/asset")
public class InOutReportController {

    @Resource
    private InOutReportService assetReportService;


    @ApiOperation("查询资产报表统计数据")
    @PostMapping(value = "/getInOutChartData")
    public CommonResult<List<InOutDTO>> getInOutChartData(@Valid @RequestBody AssetReportQueryRequest reportQueryRequest) {
        return assetReportService.getInOutChartData(reportQueryRequest);
    }


    @ApiOperation("查询收支分页数据")
    @PostMapping(value = "/listInOutAssetInfoByPage")
    public CommonResult<CommonPage<InOutDTO>> listInOutAssetInfoByPage(@Valid @RequestBody AssetReportQueryRequest reportQueryRequest) {
        return assetReportService.listInOutAssetInfoByPage(reportQueryRequest);
    }


    @ApiOperation("导出资产报表数据")
    @PostMapping(value = "/exportAsset")
    public void exportAsset( HttpServletResponse response) {
        AssetReportQueryRequest reportQueryRequest = new AssetReportQueryRequest();

          assetReportService.exportAsset(reportQueryRequest,response);
    }



}
