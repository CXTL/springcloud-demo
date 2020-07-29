package com.dupake.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinAccount;
import com.dupake.finance.entity.ReportAsset;
import com.dupake.finance.mapper.ReportAssetMapper;
import com.dupake.finance.service.FinAccountService;
import com.dupake.finance.service.FinAssetService;
import com.dupake.finance.service.ReportAssetService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 报表-资产报表(每天统计昨天数据) 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-28
 */
@Service
public class ReportAssetServiceImpl implements ReportAssetService {

    @Resource
    private ReportAssetMapper reportAssetMapper;

    /**
     * 永远统计昨天帐套数据
     */
    @Override
    public void insertReportAsset() {

        //获取昨天的开始时间 结束时间
        Long startTime = DateUtil.getMilliByTime(DateUtil.getDayStart(LocalDateTime.now().plusDays(-1)));
        Long endTime = DateUtil.getMilliByTime(DateUtil.getDayEnd(LocalDateTime.now().plusDays(-1)));
        //查询昨天的总余额 总收入 总支出
        List<ReportAsset> reportAssets = reportAssetMapper.listYesByTime(startTime, endTime);
        if (!CollectionUtils.isEmpty(reportAssets)) {
            //过滤当天无流水的帐套数据
            List<ReportAsset> noFlowReportAssetList = reportAssets.stream().filter(
                    a -> BigDecimal.ZERO.compareTo(a.getTotalAsset()) == 0
                            && BigDecimal.ZERO.compareTo(a.getTotalExpenditure()) == 0
                            && BigDecimal.ZERO.compareTo(a.getTotalIncome()) == 0
            )
                    .collect(Collectors.toList());


            // 过滤当天有流水数据
            List<ReportAsset> yesFlowReportAssetList = reportAssets.stream().filter(
                    a -> !(BigDecimal.ZERO.compareTo(a.getTotalAsset()) == 0
                            && BigDecimal.ZERO.compareTo(a.getTotalExpenditure()) == 0
                            && BigDecimal.ZERO.compareTo(a.getTotalIncome()) == 0)
            ).collect(Collectors.toList());


            if(!CollectionUtils.isEmpty(noFlowReportAssetList)){
                //帐套昨天无流水,延续帐套昨天数据, 若昨天帐套未记录数据 则不计入
                List<ReportAsset> reportAssetList =  reportAssetMapper.selectListByTime(startTime, endTime ,noFlowReportAssetList);
                //过滤当天存在流水帐套数据
                if(!CollectionUtils.isEmpty(reportAssetList)){
                    noFlowReportAssetList.forEach(a->{
                        reportAssetList.forEach(b->{
                            a.setTotalAsset(b.getTotalAsset());
                            a.setTotalIncome(b.getTotalIncome());
                            a.setTotalExpenditure(b.getTotalExpenditure());
                        });
                    });
                    yesFlowReportAssetList.addAll(noFlowReportAssetList);
                }


            }

            //落地昨天帐套数据
            yesFlowReportAssetList.forEach(a->{
                a.setCreateTime(DateUtil.getCurrentTimeMillis());
                a.setStartTime(startTime);
                a.setEndTime(endTime);
                a.setIsDeleted(YesNoSwitchEnum.NO.getValue());
                a.setTotalProfit(ArithmeticUtils.sub(a.getTotalIncome(), a.getTotalExpenditure()));
            });
            if(!CollectionUtils.isEmpty(yesFlowReportAssetList)){
                reportAssetMapper.insertBatch(yesFlowReportAssetList);
            }
        }





    }
}
