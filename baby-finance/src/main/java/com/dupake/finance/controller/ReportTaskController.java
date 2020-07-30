package com.dupake.finance.controller;

import com.dupake.finance.service.ReportAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
     * 每天12:00:10生成昨天的数据
     */
    @Scheduled(cron="10 00 00 * * ?")
    public void insertReportAsset(){
        log.info("ReportTaskController insertReportAsset time :{}", LocalDateTime.now());
        reportAssetService.insertReportAsset();
    }

}
