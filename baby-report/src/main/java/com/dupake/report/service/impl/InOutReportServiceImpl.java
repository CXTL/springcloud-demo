package com.dupake.report.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.InOutDTO;
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.report.dto.ExportAssetDTO;
import com.dupake.report.mapper.InOutReportMapper;
import com.dupake.report.service.InOutReportService;
import com.dupake.report.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
public class InOutReportServiceImpl implements InOutReportService {

    @Resource
    private InOutReportMapper inOutReportMapper;

    @Override
    public CommonResult<List<HomeReportAssetDTO>> getAssetChartData(AssetReportQueryRequest reportQueryRequest) {

        List<HomeReportAssetDTO> targetReportAssetList = new ArrayList<>();

        //时间计算 是否包含今天

        targetReportAssetList = inOutReportMapper.getReportAsset(reportQueryRequest);

        //时间计算 是否包含今天
        if(reportQueryRequest.getEndTime() > DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()))){//包含
            //查询时间段内的资产变动统计
            Long startTime = DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now()));
            Long endByTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(LocalDateTime.now()));
            reportQueryRequest.setStartTime(startTime);
            reportQueryRequest.setEndTime(endByTime);
            //查询今天的总资产 总收入 总支出  总利润 开始时间 结束时间
            HomeReportAssetDTO dto = inOutReportMapper.getHomeTableDataByTime(reportQueryRequest);
            if(!Objects.isNull(dto)){
                targetReportAssetList.add(dto);
            }
        }
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
//            currentDto.setTotalProfit(lastDto.getTotalProfit());
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
        List<HomeReportAssetDTO> list = inOutReportMapper.getAssetDataByHour(reportQueryRequest);
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
        List<HomeReportAssetDTO> list = inOutReportMapper.getAssetDataByHour(reportQueryRequest);
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
