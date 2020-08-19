package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.InOutDTO;

import java.util.List;

/**
 * @Description
 * @Authror xt
 * @Date 2020/8/4 上午11:30
 */
public interface InOutReportMapper {


    /**
     * 获取非今日的时间段内的报表数据
     * @param reportQueryRequest
     * @return
     */
    List<InOutDTO>  getReportAsset(AssetReportQueryRequest reportQueryRequest);

    List<HomeReportAssetDTO> getAssetDataByHour(AssetReportQueryRequest reportQueryRequest);
}
