package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午2:59
 */
@Mapper
public interface HomeReportMapper {


    /**
     * 获取某天的资产记录
     * @param reportQueryRequest
     * @return
     */
    HomeReportAssetDTO getHomeTableDataByTime(HomeReportQueryRequest reportQueryRequest);

    /**
     * 查询每小时资产变更记录
     * @param reportQueryRequest
     * @return
     */
    List<HomeReportAssetDTO> getHomeChartDataByHour(HomeReportQueryRequest reportQueryRequest);

    /**
     * 获取非今日的时间段内的报表数据
     * @param reportQueryRequest
     * @return
     */
    List<HomeReportAssetDTO>  getReportAsset(HomeReportQueryRequest reportQueryRequest);
}
