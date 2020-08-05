package com.dupake.report.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.report.dto.ExportAssetDTO;
import com.dupake.report.entity.User;
import com.dupake.report.mapper.AssetReportMapper;
import com.dupake.report.service.AssetReportService;
import com.dupake.report.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:26
 */
@Service
public class AssetReportServiceImpl implements AssetReportService {

    @Resource
    private AssetReportMapper assetReportMapper;

    @Override
    public CommonResult<List<HomeReportAssetDTO>> getAssetChartData(AssetReportQueryRequest reportQueryRequest) {

        List<HomeReportAssetDTO> targetReportAssetList = new ArrayList<>();

        //查询时间
        if(DateUtil.subTime(
                DateUtil.convertLongToLDT(reportQueryRequest.getStartTime()),
                DateUtil.convertLongToLDT(reportQueryRequest.getEndTime())) > 1){//按天统计

            targetReportAssetList = assetReportMapper.getReportAsset(reportQueryRequest);

            //时间计算 是否包含今天
            if(reportQueryRequest.getEndTime() > DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()))){//包含
                //查询时间段内的资产变动统计
                Long startTime = DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()));
                Long endByTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(LocalDateTime.now()));
                reportQueryRequest.setStartTime(startTime);
                reportQueryRequest.setEndTime(endByTime);
                //查询今天的总资产 总收入 总支出  总利润 开始时间 结束时间
                HomeReportAssetDTO dto = assetReportMapper.getHomeTableDataByTime(reportQueryRequest);
                if(!Objects.isNull(dto)){
                    targetReportAssetList.add(dto);
                }
            }

        }else {//按小时统计
            targetReportAssetList =  assetReportMapper.getHomeChartDataByHour(reportQueryRequest);
            if(!CollectionUtils.isEmpty(targetReportAssetList)){
                HomeReportAssetDTO firstAssetDto = targetReportAssetList.get(0);
                //验证当天第一个小时是否有资产变动
                if(ArithmeticUtils.checkAssetRecordIsZero(firstAssetDto.getTotalIncome(),firstAssetDto.getTotalExpenditure(),firstAssetDto.getTotalAsset())){
                    //查询定时统计表昨天的数据
                    reportQueryRequest.setStartTime(DateUtil.getLastTime(reportQueryRequest.getStartTime()));
                    List<HomeReportAssetDTO> reportAssetDTOs = assetReportMapper.getReportAsset(reportQueryRequest);
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
        if(ArithmeticUtils.checkAssetRecordIsZero(currentDto.getTotalIncome(),currentDto.getTotalExpenditure(),currentDto.getTotalAsset())){
            currentDto.setTotalAsset(lastDto.getTotalAsset());
            currentDto.setTotalExpenditure(lastDto.getTotalExpenditure());
            currentDto.setTotalIncome(lastDto.getTotalIncome());
            currentDto.setTotalProfit(lastDto.getTotalProfit());
        }
    }

    /**
     * 获取表单数据
     * @param reportQueryRequest
     * @return
     */
    @Override
    public CommonResult<CommonPage<HomeReportAssetDTO>> listAssetInfoByPage(AssetReportQueryRequest reportQueryRequest) {
        //按小时分组
        List<HomeReportAssetDTO> list = assetReportMapper.getAssetDataByHour(reportQueryRequest);
        return CommonResult.success(CommonPage.restPage(list,24));
    }

    /**
     * 资产数据导出
     * @param reportQueryRequest
     * @param response
     * @return
     */
    @Override
    public void exportAsset(AssetReportQueryRequest reportQueryRequest,HttpServletResponse response) {

        String fileName = "asset.xls";

        List<ExportAssetDTO> exportAssetDTOS = new ArrayList<>();
        List<HomeReportAssetDTO> list = assetReportMapper.getAssetDataByHour(reportQueryRequest);
        if(!CollectionUtils.isEmpty(list)){
            exportAssetDTOS = list.stream().map(a -> {
                ExportAssetDTO dto = new ExportAssetDTO();
                BeanUtils.copyProperties(a, dto);
                return dto;
            }).collect(Collectors.toList());
        }
        FileUtil.exportExcel(exportAssetDTOS, "资产统计", "资产统计", ExportAssetDTO.class, fileName, response);
    }
}
