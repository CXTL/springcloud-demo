package com.dupake.report.service.impl;

import com.dupake.common.constatnts.NumberConstant;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.report.AssetReportQueryRequest;
import com.dupake.common.pojo.dto.res.report.HomeReportAssetDTO;
import com.dupake.common.pojo.dto.res.report.InOutDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.common.utils.PageUtil;
import com.dupake.report.dto.ExportAssetDTO;
import com.dupake.report.mapper.InOutReportMapper;
import com.dupake.report.service.InOutReportService;
import com.dupake.report.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public CommonResult<List<InOutDTO>> getInOutChartData(AssetReportQueryRequest reportQueryRequest) {

        //查询收支记录
        Long endByTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(DateUtil.convertLongToLDT(reportQueryRequest.getEndTime())));
        reportQueryRequest.setEndTime(endByTime);
        List<InOutDTO> targetReportAssetList = inOutReportMapper.getReportAsset(reportQueryRequest);

        //构建时间list
        List<String> dateList = DateUtil.buildDateList(DateUtil.convertLongToLDT(reportQueryRequest.getStartTime())
                , DateUtil.convertLongToLDT(endByTime));

        // 组合数据
        List<InOutDTO> inOutDTOS = new ArrayList<>();

        if (!CollectionUtils.isEmpty(targetReportAssetList)) {
            Map<String, List<InOutDTO>> listMap = targetReportAssetList.stream().collect(Collectors.groupingBy(InOutDTO::getAssetDate));
            inOutDTOS = dateList.stream().map(a -> {
                InOutDTO dto = new InOutDTO();
                dto.setAssetDate(a);
                if (!CollectionUtils.isEmpty(listMap.get(a))) {
                    InOutDTO inOutDTO = listMap.get(a).get(0);
                    dto.setTotalIncome(inOutDTO.getTotalIncome());
                    dto.setTotalExpenditure(inOutDTO.getTotalExpenditure());
                } else {
                    dto.setTotalIncome(NumberConstant.BIGDECIMAL_0);
                    dto.setTotalExpenditure(NumberConstant.BIGDECIMAL_0);
                }
                return dto;
            }).collect(Collectors.toList());
        } else {
            inOutDTOS = dateList.stream().map(a -> {
                InOutDTO dto = new InOutDTO();
                dto.setAssetDate(a);
                dto.setTotalIncome(NumberConstant.BIGDECIMAL_0);
                dto.setTotalExpenditure(NumberConstant.BIGDECIMAL_0);
                return dto;
            }).collect(Collectors.toList());
        }


        return CommonResult.success(inOutDTOS);
    }


    /**
     * 获取表单数据
     *
     * @param reportQueryRequest
     * @return
     */
    @Override
    public CommonResult<CommonPage<InOutDTO>> listInOutAssetInfoByPage(AssetReportQueryRequest reportQueryRequest) {
        //按小时分组
        List<InOutDTO> inOutDTOList = getInOutChartData(reportQueryRequest).getData();
        //构建分页数据
        List<InOutDTO> targetList = buildPageData(inOutDTOList, reportQueryRequest.getPage(), reportQueryRequest.getSize());

        return CommonResult.success(CommonPage.restPage(targetList, inOutDTOList.size()));
    }

    /**
     * 构建分页数据
     *
     * @param inOutDTOList 总数据
     * @param page  分页  1
     * @param size  每页数据 10
     */
    private List<InOutDTO> buildPageData(List<InOutDTO> inOutDTOList, Integer page, Integer size) {
        return (List<InOutDTO>) PageUtil.getPageContentByApi(inOutDTOList,page,size);
    }

    /**
     * 资产数据导出
     *
     * @param reportQueryRequest
     * @param response
     * @return
     */
    @Override
    public void exportAsset(AssetReportQueryRequest reportQueryRequest, HttpServletResponse response) {

        String fileName = "asset.xls";

        List<ExportAssetDTO> exportAssetDTOS = new ArrayList<>();
        List<HomeReportAssetDTO> list = inOutReportMapper.getAssetDataByHour(reportQueryRequest);
        if (!CollectionUtils.isEmpty(list)) {
            exportAssetDTOS = list.stream().map(a -> {
                ExportAssetDTO dto = new ExportAssetDTO();
                BeanUtils.copyProperties(a, dto);
                return dto;
            }).collect(Collectors.toList());
        }
        FileUtil.exportExcel(exportAssetDTOS, "资产统计", "资产统计", ExportAssetDTO.class, fileName, response);
    }
}
