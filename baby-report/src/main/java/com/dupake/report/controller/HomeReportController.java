package com.dupake.report.controller;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.account.AccountAddRequest;
import com.dupake.common.pojo.dto.req.account.AccountUpdateRequest;
import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.common.pojo.dto.res.report.HomeAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;
import com.dupake.report.service.HomeReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午2:02
 */
@Api(tags = "报表：首页信息")
@RestController
@RequestMapping("/report/home")
public class HomeReportController {

    @Resource
    private HomeReportService homeReportService;

    @ApiOperation("查询首页报表头数据")
    @PostMapping(value = "/getHomeTableData")
    public CommonResult<HomeTableDTO> getHomeTableData(@Valid @RequestBody HomeReportQueryRequest reportQueryRequest) {
        return homeReportService.getHomeTableData(reportQueryRequest);
    }


    @ApiOperation("查询首页报表资产数据")
    @PostMapping(value = "/getHomeAssetData")
    public CommonResult<HomeAssetDTO> getHomeAssetData(@Valid @RequestBody HomeReportQueryRequest reportQueryRequest) {
        return homeReportService.getHomeAssetData(reportQueryRequest);
    }



    @ApiOperation("查询首页报表统计数据")
    @PostMapping(value = "/getHomeChartData")
    public CommonResult getHomeChartData(@Valid @RequestBody HomeReportQueryRequest reportQueryRequest) {
        return homeReportService.getHomeChartData(reportQueryRequest);
    }


//    @ApiOperation("查询首页待处理数据")
//    @GetMapping(value = "/getHomeWaitData")
//    public CommonResult getHomeWaitData(@RequestParam(value = "accountCode") String accountCode) {
//        return homeReportService.getHomeWaitData(accountCode);
//    }


}
