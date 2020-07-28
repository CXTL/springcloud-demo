package com.dupake.report.mapper;

import com.dupake.common.pojo.dto.req.report.HomeReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeTableDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/28 下午2:59
 */
@Mapper
public interface HomeReportMapper {


    HomeTableDTO getHomeTableData(HomeReportQueryRequest reportQueryRequest);
}
