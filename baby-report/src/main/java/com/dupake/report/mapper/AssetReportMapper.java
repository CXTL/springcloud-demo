package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.AssetInfoDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:30
 */
public interface AssetReportMapper {

    /**
     * 获取某天的资产记录
     * @param reportQueryRequest
     * @return
     */
    HomeReportAssetDTO getHomeTableDataByTime(AssetReportQueryRequest reportQueryRequest);

    /**
     * 查询每小时资产变更记录
     * @param reportQueryRequest
     * @return
     */
    List<HomeReportAssetDTO> getHomeChartDataByHour(AssetReportQueryRequest reportQueryRequest);

    /**
     * 获取非今日的时间段内的报表数据
     * @param reportQueryRequest
     * @return
     */
    List<HomeReportAssetDTO>  getReportAsset(AssetReportQueryRequest reportQueryRequest);

    List<HomeReportAssetDTO> getAssetDataByHour(AssetReportQueryRequest reportQueryRequest);
}
