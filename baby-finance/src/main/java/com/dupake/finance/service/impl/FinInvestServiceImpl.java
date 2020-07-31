package com.dupake.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.invest.InvestAddRequest;
import com.dupake.common.pojo.dto.req.invest.InvestQueryRequest;
import com.dupake.common.pojo.dto.req.invest.InvestUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.InvestDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinInvest;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinInvestMapper;
import com.dupake.finance.service.BaseService;
import com.dupake.finance.service.FinAssetService;
import com.dupake.finance.service.FinInvestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 财务-投资信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
@Slf4j
public class FinInvestServiceImpl extends BaseService implements FinInvestService {


    @Resource
    private FinInvestMapper finInvestMapper;

    @Resource
    private FinAssetService assetService;



    /**
     * @param investQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.finance.InvestDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<InvestDTO>> listByPage(InvestQueryRequest investQueryRequest) {


        List<InvestDTO> investDTOS = new ArrayList<>();
        Map<String, String> accountMap = getAccountMap();
        Map<String, String> subjectMap = getSubjectMap();

        int totalCount = finInvestMapper.getTotalCount(investQueryRequest);
        if (totalCount > 0) {
            List<FinInvest> finInvests = finInvestMapper.selectListPage(investQueryRequest);
            if (!ObjectUtils.isEmpty(finInvests)) {
                investDTOS = finInvests.stream().map(a -> {
                    InvestDTO dto = new InvestDTO();
                    BeanUtils.copyProperties(a, dto);
                    dto.setAccountName(accountMap.get(dto.getAccountCode()));
                    dto.setSubjectName(subjectMap.get(dto.getSubjectCode()));
                    return dto;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(investDTOS, totalCount));
    }


    /**
     * @param investAddRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 新增投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addInvest(InvestAddRequest investAddRequest) {

        //落地投资数据
        try {

            FinInvest invest = FinInvest.builder()
                    .accountCode(investAddRequest.getAccountCode())
                    .investAmount(investAddRequest.getInvestAmount())
                    .investFund(investAddRequest.getInvestFund())
                    .investName(investAddRequest.getInvestName())
                    .investRatio(investAddRequest.getInvestRatio())
                    .subjectCode(investAddRequest.getSubjectCode())
                    .remark(investAddRequest.getRemark())
                    .build();

            finInvestMapper.insert(invest);

            //落地资产数据
            assetService.addAssetInvest(invest);


        } catch (Exception e) {
            log.error("FinInvestServiceImpl add invest error , param:{}, error:{}", JSONObject.toJSONString(investAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }

//    /**
//     * 投资名称唯一性校验
//     * @param investName
//     */
//    private void checkInvestInfo(String investName) {
//        FinInvest finInvest = finInvestMapper.selectOne(
//                new LambdaQueryWrapper<FinInvest>()
//                        .eq(FinInvest::getInvestName, investName)
//                        .eq(FinInvest::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
//        if(!Objects.isNull(finInvest)){
//            log.error("FinInvestServiceImpl finInvest is exist , param:{}", JSONObject.toJSONString(investName));
//            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
//        }
//    }


    /**
     * @param investUpdateRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 修改投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateInvest(InvestUpdateRequest investUpdateRequest) {

        FinInvest finInvest = finInvestMapper.selectOne(new LambdaQueryWrapper<FinInvest>()
                .eq(FinInvest::getId, investUpdateRequest.getId())
                .eq(FinInvest::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(finInvest)){
            log.error("invest is null");
            throw new BadRequestException(BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getCode(), BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getMessage());
        }
        //投资名称校验 权限标识校验
//        checkInvestInfo(investUpdateRequest.getName(), investUpdateRequest.getPermission(),finInvest);

        //修改投资信息
        try {
            finInvestMapper.updateById(FinInvest.builder()
                    .accountCode(investUpdateRequest.getAccountCode())
                    .investAmount(investUpdateRequest.getInvestAmount())
                    .investFund(investUpdateRequest.getInvestFund())
                    .investName(investUpdateRequest.getInvestName())
                    .investRatio(investUpdateRequest.getInvestRatio())
                    .remark(investUpdateRequest.getRemark())
                    .id(investUpdateRequest.getId())
                    .build());
        } catch (Exception e) {
            log.error("FinInvestServiceImpl update invest error , param:{}, error:{}", JSONObject.toJSONString(investUpdateRequest), e);
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
    public CommonResult deleteInvest(List<Long> ids) {

        //批量修改投资状态
        List<FinInvest> finInvestList = ids.stream().map(a -> {
            FinInvest finInvest = new FinInvest();
            finInvest.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            finInvest.setUpdateTime(DateUtil.getCurrentTimeMillis());
            finInvest.setId(a);
            return finInvest;
        }).collect(Collectors.toList());

        try {
            finInvestMapper.updateBatch(finInvestList);
        } catch (Exception e) {
            log.error("FinInvestServiceImpl update invest error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        //todo 修改角色投资表数据
        return CommonResult.success();
    }
    
}
