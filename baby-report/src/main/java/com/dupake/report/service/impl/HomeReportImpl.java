package com.dupake.report.service.impl;

import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.report.mapper.HomeReportMapper;
import com.dupake.report.service.HomeReportService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


        reportQueryRequest.setStartTime(DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now())));
        reportQueryRequest.setEndTime(DateUtil.getMilliByTime(DateUtil.getDayEnd(LocalDateTime.now())));

        //查询今日资产 收入 支出
        HomeReportAssetDTO tableDataToday = homeReportMapper.getHomeTableDataByTime(reportQueryRequest);
        BigDecimal incomeToday = tableDataToday.getTotalIncome();
        BigDecimal expenditureToday = tableDataToday.getTotalExpenditure();

        //查询昨日资产 收入 支出
        reportQueryRequest.setStartTime(DateUtil.getLastTime(reportQueryRequest.getStartTime()));
        reportQueryRequest.setEndTime(DateUtil.getLastTime(reportQueryRequest.getEndTime()));

        HomeReportAssetDTO tableDataYes = homeReportMapper.getHomeTableDataByTime(reportQueryRequest);

        BigDecimal incomeYes = tableDataYes.getTotalIncome();
        BigDecimal expenditureYes = tableDataYes.getTotalExpenditure();

        //计算利润
        BigDecimal proToday = ArithmeticUtils.sub(incomeToday, expenditureToday);
        BigDecimal proYes = ArithmeticUtils.sub(incomeYes,expenditureYes);

        //今日总资产 昨日总资产
        BigDecimal totalAssetToday = tableDataToday.getTotalAsset();
        BigDecimal totalAssetYes = tableDataYes.getTotalAsset();

        //计算今日利润 昨日利润 收入 支出 利润 比例
        HomeTableDTO tableDTO = HomeTableDTO.builder()
                .incomeToday(incomeToday)
                .expenditureToday(expenditureToday)
                .profitToday(proToday)
                .assetToday(totalAssetToday)

                .incomeYesterday(incomeYes)
                .expenditureYesterday(expenditureYes)
                .profitYesterday(proYes)
                .assetYesterday(totalAssetYes)

                .rateIncome(ArithmeticUtils.countRate(incomeToday,incomeYes))
                .rateExpenditure(ArithmeticUtils.countRate(expenditureToday,expenditureYes))
                .rateProfit(ArithmeticUtils.countRate(proToday,proYes))
                .rateAsset(ArithmeticUtils.countRate(totalAssetToday,totalAssetYes))


                .build();

        return CommonResult.success(tableDTO);
    }

    @Override
    public CommonResult<HomeAssetDTO> getHomeAssetData(HomeReportQueryRequest reportQueryRequest) {

        //查询今天的总资产 和 总收入和 总支出
//        HomeReportAssetDTO dtoDay =  homeReportMapper.getHomeAssetData(reportQueryRequest);

        //计算上月初开始时间和上月末结束时间
        Long firstTimeLastMonth = DateUtil.getMilliByTime(DateUtil.getMonthStartByOffset(-1));
        Long endTimeLastMonth = DateUtil.getMilliByTime(DateUtil.getMonthEndByOffset(-1));

        //计算上周初开始时间和上周末结束时间
        Long firstTimeLastWeek = DateUtil.getMilliByTime(DateUtil.weekStartByOffset(-1));
        Long endTimeLastWeek = DateUtil.getMilliByTime(DateUtil.getMonthEndByOffset(-1));

        //计算本月初到昨天的开始时间和结束时间

        //计算本周到昨天的开始时间和结束时间


        //(除去今天)获取本周总资产 和 总收入 和 总支出


        //(除去今天)获取本月总资产 和 总收入 和 总支出


        //计算本周本月的 总利润

        //计算 本月/周 和 上月/周的 比例


        return CommonResult.success();
    }

    @Override
    public CommonResult<List<HomeReportAssetDTO>> getHomeChartData(HomeReportQueryRequest reportQueryRequest) {

        List<HomeReportAssetDTO> targetReportAssetList = new ArrayList<>();
        
        //查询时间
        if(DateUtil.subTime(
                DateUtil.convertLongToLDT(reportQueryRequest.getStartTime()),
                DateUtil.convertLongToLDT(reportQueryRequest.getEndTime())) > 1){//按天统计

            targetReportAssetList = homeReportMapper.getReportAsset(reportQueryRequest);

            //时间计算 是否包含今天
            if(reportQueryRequest.getEndTime() > DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()))){//包含
                //查询时间段内的资产变动统计
                Long startTime = DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()));
                Long endByTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(LocalDateTime.now()));
                reportQueryRequest.setStartTime(startTime);
                reportQueryRequest.setEndTime(endByTime);
                //查询今天的总资产 总收入 总支出  总利润 开始时间 结束时间
                HomeReportAssetDTO dto = homeReportMapper.getHomeTableDataByTime(reportQueryRequest);
                if(!Objects.isNull(dto)){
                    targetReportAssetList.add(dto);
                }
            }

        }else {//按小时统计
             targetReportAssetList =   homeReportMapper.getHomeChartDataByHour(reportQueryRequest);
            if(!CollectionUtils.isEmpty(targetReportAssetList)){
                HomeReportAssetDTO firstAssetDto = targetReportAssetList.get(0);
                //验证当天第一个小时是否有资产变动
                if(checkAssetRecordIsZero(firstAssetDto.getTotalIncome(),firstAssetDto.getTotalExpenditure(),firstAssetDto.getTotalAsset())){
                    //查询定时统计表昨天的数据
                    reportQueryRequest.setStartTime(DateUtil.getLastTime(reportQueryRequest.getStartTime()));
                    List<HomeReportAssetDTO> reportAssetDTOs = homeReportMapper.getReportAsset(reportQueryRequest);
                    if(!CollectionUtils.isEmpty(reportAssetDTOs)){
                        HomeReportAssetDTO reportAssetDTO = reportAssetDTOs.get(0);
                        firstAssetDto.setTotalAsset(reportAssetDTO.getTotalAsset());
                        firstAssetDto.setTotalExpenditure(reportAssetDTO.getTotalExpenditure());
                        firstAssetDto.setTotalIncome(reportAssetDTO.getTotalIncome());
                        firstAssetDto.setTotalProfit(reportAssetDTO.getTotalProfit());
                    }
                }
                //转换数据
                for(int i =1 ; i<targetReportAssetList.size(); i++){
                    HomeReportAssetDTO lastDto = targetReportAssetList.get(i - 1);
                    HomeReportAssetDTO currentDto = targetReportAssetList.get(i);
                    conversionAssetData(lastDto,currentDto);
                }
            }
        }

//        if(!CollectionUtils.isEmpty(targetReportAssetList)){
//
//        }

        return CommonResult.success(targetReportAssetList);
    }


    /**
     * 数据转换
     * @param lastDto 上一条数据
     * @param currentDto 当前数据
     */
    private void conversionAssetData(HomeReportAssetDTO lastDto, HomeReportAssetDTO currentDto){
        //当前时间资产记录无变动
        if(checkAssetRecordIsZero(currentDto.getTotalIncome(),currentDto.getTotalExpenditure(),currentDto.getTotalAsset())){
            currentDto.setTotalAsset(lastDto.getTotalAsset());
            currentDto.setTotalExpenditure(lastDto.getTotalExpenditure());
            currentDto.setTotalIncome(lastDto.getTotalIncome());
            currentDto.setTotalProfit(lastDto.getTotalProfit());
        }
    }



    /**
     * 校验资产 收入 支出 是否都为0
     * @param value
     * @return
     */
    private Boolean checkAssetRecordIsZero( BigDecimal... value ){
        int count = 0;
        for(BigDecimal v : value){
            if(ArithmeticUtils.checkBigDecimalZero(v)){
                count ++;
            }
        }
        return count == value.length ? true : false;
    }
}
