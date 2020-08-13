package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.AssetInfoDTO;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:30
 */
public interface InOutReportMapper {

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
     * @param
     * @return
     */
    List<HomeReportAssetDTO>  getReportAsset(@Param("startTime") Long startTime,@Param("endTime") Long endTime,
                                             @Param("accountCode") String accountCode,@Param("subjectCode") String subjectCode,
                                             @Param("inType") Integer inType,@Param("outType") Integer outType);

    List<HomeReportAssetDTO> getAssetDataByHour(AssetReportQueryRequest reportQueryRequest);
}
