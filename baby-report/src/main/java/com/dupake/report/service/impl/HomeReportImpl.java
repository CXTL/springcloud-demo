package com.dupake.report.service.impl;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;
import com.dupake.common.pojo.dto.res.report.HomeAssetDTO;
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.report.mapper.HomeReportMapper;
import com.dupake.report.service.HomeReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午2:05
 */
@Service
public class HomeReportImpl implements HomeReportService {


    @Resource
    private HomeReportMapper homeReportMapper;

    @Override
    public CommonResult<HomeTableDTO> getHomeTableData(HomeReportQueryRequest reportQueryRequest) {
        //查询今日收入 支出
        HomeTableDTO tableDataToday = homeReportMapper.getHomeTableData(reportQueryRequest);
        BigDecimal incomeToday = tableDataToday.getIncomeToday();
        BigDecimal expenditureToday = tableDataToday.getExpenditureToday();

        //查询昨日收入 支出 todo 计算昨天时间
        reportQueryRequest.setStartTime(DateUtil.getLastTime(reportQueryRequest.getStartTime()));
        reportQueryRequest.setEndTime(DateUtil.getLastTime(reportQueryRequest.getEndTime()));

        HomeTableDTO tableDataYes = homeReportMapper.getHomeTableData(reportQueryRequest);
        BigDecimal incomeYes = tableDataYes.getIncomeToday();
        BigDecimal expenditureYes = tableDataYes.getExpenditureToday();

        //计算利润
        BigDecimal proToday = ArithmeticUtils.sub(incomeToday, expenditureToday);
        BigDecimal proYes = ArithmeticUtils.sub(incomeYes,expenditureYes);

        //计算今日利润 昨日利润 收入 支出 利润 比例
        HomeTableDTO tableDTO = HomeTableDTO.builder()
                .incomeToday(incomeToday)
                .expenditureToday(expenditureToday)
                .profitToday(proToday)

                .incomeYesterday(incomeYes)
                .expenditureYesterday(expenditureYes)
                .profitYesterday(proYes)

                .rateIncome(ArithmeticUtils.countRate(incomeToday,incomeYes))
                .rateExpenditure(ArithmeticUtils.countRate(expenditureToday,expenditureYes))
                .rateProfit(ArithmeticUtils.countRate(proToday,proYes))

                .build();

        return CommonResult.success(tableDTO);
    }

    @Override
    public CommonResult<HomeAssetDTO> getHomeAssetData(HomeReportQueryRequest reportQueryRequest) {
        return null;
    }

    @Override
    public CommonResult getHomeChartData(HomeReportQueryRequest reportQueryRequest) {
        return null;
    }
}
