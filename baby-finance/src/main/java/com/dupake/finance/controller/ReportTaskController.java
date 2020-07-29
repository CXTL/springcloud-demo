package com.dupake.finance.controller;

import com.dupake.finance.service.ReportAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Authror xt
 * @Date 2020/7/29 下午2:31
 */
@Component
@Slf4j
public class ReportTaskController {

    @Resource
    private ReportAssetService reportAssetService;

    /**
     * 每天一点生成昨天的数据
     */
//    @Scheduled(cron="*/6 * * * * ?")
    public void insertReportAsset(){
        reportAssetService.insertReportAsset();
    }

}
