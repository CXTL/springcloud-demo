package com.dupake.report.controller;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.InvestReportDTO;
import com.dupake.report.service.InvestReportService;
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
@Api(tags = "报表：投资信息")
@RestController
@RequestMapping("/report/invest")
public class InvestReportController {

    @Resource
    private InvestReportService investReportService;


    @ApiOperation("查看投资报表统计数据")
    @PostMapping(value = "/getInvestChartData")
    public CommonResult<List<InvestReportDTO>> getInvestChartData(@Valid @RequestBody AssetReportQueryRequest reportQueryRequest) {
        return investReportService.getInvestChartData(reportQueryRequest);
    }


    @ApiOperation("查询投资分页数据")
    @PostMapping(value = "/listInvestAssetInfoByPage")
    public CommonResult<CommonPage<InvestReportDTO>> listinvestReportServiceInvestAssetInfoByPage(@Valid @RequestBody AssetReportQueryRequest reportQueryRequest) {
        return investReportService.listInvestInfoByPage(reportQueryRequest);
    }


    @ApiOperation("导出投资报表数据")
    @PostMapping(value = "/exportInvest")
    public void exportInvest( HttpServletResponse response) {
        AssetReportQueryRequest reportQueryRequest = new AssetReportQueryRequest();

          investReportService.exportInvest(reportQueryRequest,response);
    }



}
