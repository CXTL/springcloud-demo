package com.dupake.report.controller;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;
import com.dupake.report.service.HomeReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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



    @ApiOperation("查询首页报表统计数据")
    @PostMapping(value = "/getHomeChartData")
    public CommonResult<List<HomeReportAssetDTO>> getHomeChartData(@Valid @RequestBody HomeReportQueryRequest reportQueryRequest) {
        return homeReportService.getHomeChartData(reportQueryRequest);
    }




}
