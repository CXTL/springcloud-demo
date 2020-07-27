package com.dupake.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.constatnts.NumberConstant;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.asset.AssetAddRequest;
import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.common.pojo.dto.req.asset.AssetUpdateRequest;
import com.dupake.common.pojo.dto.req.asset.AssetAddRequest;
import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.common.pojo.dto.req.asset.AssetUpdateRequest;
import com.dupake.common.pojo.dto.res.AssetDTO;
import com.dupake.common.pojo.dto.res.AssetDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinAsset;
import com.dupake.finance.entity.FinAssetRecord;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinAssetMapper;
import com.dupake.finance.mapper.FinAssetRecordMapper;
import com.dupake.finance.service.FinAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 财务-余额信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
@Slf4j
public class FinAssetServiceImpl implements FinAssetService {

    @Resource
    private FinAssetMapper finAssetMapper;
    
    @Resource
    private FinAssetRecordMapper finAssetRecordMapper;
    
    /**
     * @param assetQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.AssetDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<AssetDTO>> listByPage(AssetQueryRequest assetQueryRequest) {
        List<AssetDTO> assetDTOS = new ArrayList<>();

        int totalCount = finAssetRecordMapper.getTotalCount(assetQueryRequest);
        if (totalCount > 0) {
            List<FinAssetRecord> finAssets = finAssetRecordMapper.selectListPage(assetQueryRequest);
            if (!ObjectUtils.isEmpty(finAssets)) {
                assetDTOS = finAssets.stream().map(a -> {
                    AssetDTO assetDTO = new AssetDTO();
                    BeanUtils.copyProperties(a, assetDTO);
                    return assetDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(assetDTOS, totalCount));
    }


    /**
     * @param assetAddRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 新增投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addAsset(AssetAddRequest assetAddRequest) {
        //投资名称校验 权限标识校验
//        checkAssetInfo(assetAddRequest.getName(), assetAddRequest.getPermission(),null);

        try {

            //落地投资总数据 查询后计算
            FinAsset asset = FinAsset.builder()
                    .accountCode(assetAddRequest.getAccountCode())
                    .freezeBalance(NumberConstant.BIGDECIMAL_0)
                    .availableBalance(NumberConstant.BIGDECIMAL_0)
                    .remark("test")
                    .status(1)
                    .totalBalance(NumberConstant.BIGDECIMAL_0)
                    .build();
            finAssetMapper.insert(asset);

            //落地投资数据详情
            finAssetRecordMapper.insert(FinAssetRecord.builder()
                    .accountCode(assetAddRequest.getAccountCode())
                    .amount(assetAddRequest.getRealPayAmount().add(assetAddRequest.getRealReceiveAmount()))
                    .balanceAfter(NumberConstant.BIGDECIMAL_0)
                    .balanceBefore(NumberConstant.BIGDECIMAL_0)
                    .payAmount(assetAddRequest.getPayAmount())
                    .realPayAmount(assetAddRequest.getRealPayAmount())
                    .realReceiveAmount(assetAddRequest.getRealReceiveAmount())
                    .receiveAmount(assetAddRequest.getReceiveAmount())
                    .subjectCode(assetAddRequest.getSubjectCode())
                    .remark(assetAddRequest.getRemark())
                    .type(assetAddRequest.getType())
                    .assetId(asset.getId())
                    .build());


        } catch (Exception e) {
            log.error("FinAssetServiceImpl add asset error , param:{}, error:{}", JSONObject.toJSONString(assetAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * @param assetUpdateRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 修改投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateAsset(AssetUpdateRequest assetUpdateRequest) {


        try {

        //落地投资数据详情
        finAssetRecordMapper.updateById(FinAssetRecord.builder()
                .accountCode(assetUpdateRequest.getAccountCode())
                .amount(assetUpdateRequest.getRealPayAmount().add(assetUpdateRequest.getRealReceiveAmount()))
                .balanceAfter(NumberConstant.BIGDECIMAL_0)
                .balanceBefore(NumberConstant.BIGDECIMAL_0)
                .payAmount(assetUpdateRequest.getPayAmount())
                .realPayAmount(assetUpdateRequest.getRealPayAmount())
                .realReceiveAmount(assetUpdateRequest.getRealReceiveAmount())
                .receiveAmount(assetUpdateRequest.getReceiveAmount())
                .subjectCode(assetUpdateRequest.getSubjectCode())
                .remark(assetUpdateRequest.getRemark())
                .type(assetUpdateRequest.getType())
                .id(assetUpdateRequest.getId())
                .build());

            //  修改投资信息
            finAssetMapper.updateById(FinAsset.builder()
                    .accountCode(assetUpdateRequest.getAccountCode())
                    .freezeBalance(NumberConstant.BIGDECIMAL_0)
                    .availableBalance(NumberConstant.BIGDECIMAL_0)
                    .remark("test")
                    .status(1)
                    .totalBalance(NumberConstant.BIGDECIMAL_0)
                    .id(assetUpdateRequest.getAssetId())
                    .build());
        } catch (Exception e) {
            log.error("FinAssetServiceImpl update asset error , param:{}, error:{}", JSONObject.toJSONString(assetUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }


        return CommonResult.success();
    }

    /**
     * @param ids :
     * @return com.dupake.common.message.CommonResult
     * @Description
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteAsset(List<Long> ids) {

        //根据资产ID分组查询 需删除的资产金额总和
        List<FinAssetRecord> finAssetRecords = finAssetRecordMapper.selectList(
                new LambdaUpdateWrapper<FinAssetRecord>()
                        .eq(FinAssetRecord::getIsDeleted, YesNoSwitchEnum.NO.getValue())
                        .in(FinAssetRecord::getId, ids)
        );
        if(!CollectionUtils.isEmpty(finAssetRecords)){
            Map<Long, BigDecimal> recordMap = finAssetRecords.stream().collect(Collectors.toMap(FinAssetRecord::getAssetId, FinAssetRecord::getAmount));

            //批量修改投资状态
            List<FinAsset> finAssets = finAssetMapper.selectList(new LambdaQueryWrapper<FinAsset>()
                    .eq(FinAsset::getIsDeleted, YesNoSwitchEnum.NO.getValue())
                    .in(FinAsset::getId, recordMap.keySet())
            );
            finAssets.forEach(a->{
                a.setTotalBalance(a.getTotalBalance().add(recordMap.get(a.getId())));
            });

            finAssetMapper.updateBatch(finAssets);

        }


        List<FinAssetRecord> finAssetRecordList = ids.stream().map(a -> {
            FinAssetRecord assetRecord = new FinAssetRecord();
            assetRecord.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            assetRecord.setUpdateTime(DateUtil.getCurrentTimeMillis());
            assetRecord.setId(a);
            return assetRecord;
        }).collect(Collectors.toList());

        try {
            finAssetRecordMapper.updateBatch(finAssetRecordList);
        } catch (Exception e) {
            log.error("FinAssetServiceImpl update asset error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        //todo 修改角色投资表数据
        return CommonResult.success();
    }
}
