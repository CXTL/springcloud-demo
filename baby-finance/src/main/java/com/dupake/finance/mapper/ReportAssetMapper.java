package com.dupake.finance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dupake.finance.entity.ReportAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 报表-资产报表(每天统计) Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-28
 */
public interface ReportAssetMapper extends BaseMapper<ReportAsset> {

    List<ReportAsset> listYesByTime(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    List<ReportAsset> selectListByTime(@Param("startTime")Long startTime, @Param("endTime") Long endTime,
                                       @Param("list") List<ReportAsset> reportAssetList);

    void insertBatch(List<ReportAsset> yesFlowReportAssetList);
}
