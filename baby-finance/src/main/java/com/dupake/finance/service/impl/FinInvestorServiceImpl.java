package com.dupake.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.investor.InvestorAddRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorQueryRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.InvestorDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinInvestor;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinInvestorMapper;
import com.dupake.finance.service.BaseService;
import com.dupake.finance.service.FinInvestorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 财务-投资人信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
@Slf4j
public class FinInvestorServiceImpl extends BaseService implements FinInvestorService {

    @Resource
    private FinInvestorMapper finInvestorMapper;


    /**
     * @param investorQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.finance.InvestorDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<InvestorDTO>> listByPage(InvestorQueryRequest investorQueryRequest) {
        List<InvestorDTO> investorDTOS = new ArrayList<>();

        int totalCount = finInvestorMapper.getTotalCount(investorQueryRequest);
        if (totalCount > 0) {
            List<FinInvestor> finInvestors = finInvestorMapper.selectListPage(investorQueryRequest);
            if (!ObjectUtils.isEmpty(finInvestors)) {
                investorDTOS = finInvestors.stream().map(a -> {
                    InvestorDTO investorDTO = new InvestorDTO();
                    BeanUtils.copyProperties(a, investorDTO);
                    return investorDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(investorDTOS, totalCount));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addInvestor(InvestorAddRequest investorAddRequest) {
        // 校验投资人名称是否重复
        checkInvestorByName(investorAddRequest.getInvestorName());

        try {
            finInvestorMapper.insert(FinInvestor.builder()
                    .investorAddress(investorAddRequest.getInvestorAddress())
                    .investorName(investorAddRequest.getInvestorName())
                    .investorPhone(investorAddRequest.getInvestorPhone())
                    .remark(investorAddRequest.getRemark())
                    .build());
        } catch (Exception e) {
            log.error("FinInvestorServiceImpl add investor error , param:{}, error:{}", JSONObject.toJSONString(investorAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * 校验投资人名称
     *
     * @param name
     */
    private void checkInvestorByName(String name) {
        FinInvestor finInvestor = finInvestorMapper.selectOne(
                new LambdaQueryWrapper<FinInvestor>()
                        .eq(FinInvestor::getInvestorName, name)
                        .eq(FinInvestor::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if (!Objects.isNull(finInvestor)) {
            throw new BadRequestException(BaseResult.FIN_INVESTOR_NAME_IS_EXIST.getCode(), BaseResult.FIN_INVESTOR_NAME_IS_EXIST.getMessage());
        }
    }

    ;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateInvestor(InvestorUpdateRequest investorUpdateRequest) {

        FinInvestor finInvestor = finInvestorMapper.selectOne(
                new LambdaQueryWrapper<FinInvestor>().
                        eq(FinInvestor::getId, investorUpdateRequest.getId()).
                        eq(FinInvestor::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(finInvestor)){
            throw new BadRequestException(BaseResult.FIN_INVESTOR_INFO_IS_NOT_EXIST.getCode(),
                    BaseResult.FIN_INVESTOR_INFO_IS_NOT_EXIST.getMessage());
        }
        // 校验投资人名称是否重复
        if(!finInvestor.getInvestorName().equals(investorUpdateRequest.getInvestorName())){
            checkInvestorByName(investorUpdateRequest.getInvestorName());
        }

        try {
            //落地投资数据详情
            finInvestorMapper.updateById(FinInvestor.builder()
                    .investorAddress(investorUpdateRequest.getInvestorAddress())
                    .investorName(investorUpdateRequest.getInvestorName())
                    .investorPhone(investorUpdateRequest.getInvestorPhone())
                    .id(investorUpdateRequest.getId())
                    .remark(investorUpdateRequest.getRemark())
                    .build());

        } catch (Exception e) {
            log.error("FinInvestorServiceImpl update investor error , param:{}, error:{}", JSONObject.toJSONString(investorUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }


        return CommonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteInvestor(List<Long> ids) {

        List<FinInvestor> finInvestors = ids.stream().map(a -> {
            FinInvestor finInvestor = FinInvestor.builder().build();
            finInvestor.setId(a);
            finInvestor.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            finInvestor.setUpdateTime(DateUtil.getCurrentTimeMillis());
            return finInvestor;
        }).collect(Collectors.toList());

        try {
            if (!CollectionUtils.isEmpty(finInvestors)) {
                finInvestorMapper.updateBatch(finInvestors);
            }
        } catch (Exception e) {
            log.error("FinInvestorServiceImpl delete finInvestor error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        return CommonResult.success();
    }

    /**
     *获取投资人列表
     * @return
     */
    @Override
    public CommonResult<List<InvestorDTO>> listInvestor() {

        List<FinInvestor> finInvestors = finInvestorMapper.selectList(
                new LambdaQueryWrapper<FinInvestor>()
                        .eq(FinInvestor::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        List<InvestorDTO> investorDTOS = new ArrayList<>(finInvestors.size());
        if(!CollectionUtils.isEmpty(finInvestors)){
            investorDTOS = finInvestors.stream().map(a -> {
                InvestorDTO dto = new InvestorDTO();
                BeanUtils.copyProperties(a, dto);
                return dto;
            }).collect(Collectors.toList());
        }
        return CommonResult.success(investorDTOS);
    }
}
