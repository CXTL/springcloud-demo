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
import com.dupake.common.utils.ArithmeticUtils;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinInvestFlow;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinInvestFlowMapper;
import com.dupake.finance.service.BaseService;
import com.dupake.finance.service.FinAssetService;
import com.dupake.finance.service.FinInvestFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class FinInvestFlowFlowServiceImpl extends BaseService implements FinInvestFlowService {


    @Resource
    private FinInvestFlowMapper finInvestFlowMapper;

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

        int totalCount = finInvestFlowMapper.getTotalCount(investQueryRequest);
        if (totalCount > 0) {
            List<FinInvestFlow> finInvestFlows = finInvestFlowMapper.selectListPage(investQueryRequest);
            if (!ObjectUtils.isEmpty(finInvestFlows)) {
                investDTOS = finInvestFlows.stream().map(a -> {
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

            //查询该帐套下该投资人是否投资过
            FinInvestFlow lastFinInvestFlow = finInvestFlowMapper.selectInfoByAccountCode(
                    investAddRequest.getAccountCode(),investAddRequest.getInvestorId());

            BigDecimal totalInvestAmount = !Objects.isNull(lastFinInvestFlow) ?
                    ArithmeticUtils.add(investAddRequest.getActualInvestAmount(), lastFinInvestFlow.getTotalInvestAmount())
                    : investAddRequest.getActualInvestAmount();

            FinInvestFlow invest = FinInvestFlow.builder()
                    .accountCode(investAddRequest.getAccountCode())
                    .totalInvestAmount(totalInvestAmount)
                    .actualInvestAmount(investAddRequest.getActualInvestAmount())
                    .investorId(investAddRequest.getInvestorId())
                    .investRatio(ArithmeticUtils.div(investAddRequest.getActualInvestAmount(),totalInvestAmount).doubleValue())
                    .subjectCode(investAddRequest.getSubjectCode())
                    .remark(investAddRequest.getRemark())
                    .investDate(investAddRequest.getInvestDate())
                    .shouldInvestAmount(investAddRequest.getShouldInvestAmount())
                    .build();

            finInvestFlowMapper.insert(invest);

            //落地资产数据


        } catch (Exception e) {
            log.error("FinInvestServiceImpl add invest error , param:{}, error:{}", JSONObject.toJSONString(investAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }



    /**
     * @param investUpdateRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 修改投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateInvest(InvestUpdateRequest investUpdateRequest) {

        FinInvestFlow finInvestFlow = finInvestFlowMapper.selectOne(new LambdaQueryWrapper<FinInvestFlow>()
                .eq(FinInvestFlow::getId, investUpdateRequest.getId())
                .eq(FinInvestFlow::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(finInvestFlow)){
            log.error("invest is null");
            throw new BadRequestException(BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getCode(), BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getMessage());
        }
        //投资名称校验 权限标识校验
//        checkInvestInfo(investUpdateRequest.getName(), investUpdateRequest.getPermission(),finInvest);


        //计算投资金额 投资比例
        BigDecimal oldInvestAmount = ArithmeticUtils.sub(finInvestFlow.getTotalInvestAmount(), finInvestFlow.getActualInvestAmount());
        BigDecimal newInvestAmount = ArithmeticUtils.add(oldInvestAmount,investUpdateRequest.getActualInvestAmount());
        Double investRatio =  ArithmeticUtils.div(investUpdateRequest.getActualInvestAmount(),newInvestAmount).doubleValue();



        //修改投资信息
        try {
            finInvestFlowMapper.updateById(FinInvestFlow.builder()
                    .accountCode(investUpdateRequest.getAccountCode())
                    .totalInvestAmount(newInvestAmount)
                    .actualInvestAmount(investUpdateRequest.getActualInvestAmount())
                    .investorId(investUpdateRequest.getInvestorId())
                    .investRatio(investRatio)
                    .remark(investUpdateRequest.getRemark())
                    .shouldInvestAmount(investUpdateRequest.getShouldInvestAmount())
                    .investDate(investUpdateRequest.getInvestDate())
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
        List<FinInvestFlow> finInvestFlowList = ids.stream().map(a -> {
            FinInvestFlow finInvestFlow = new FinInvestFlow();
            finInvestFlow.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            finInvestFlow.setUpdateTime(DateUtil.getCurrentTimeMillis());
            finInvestFlow.setId(a);
            return finInvestFlow;
        }).collect(Collectors.toList());

        try {
            finInvestFlowMapper.updateBatch(finInvestFlowList);
        } catch (Exception e) {
            log.error("FinInvestServiceImpl update invest error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        //todo 修改角色投资表数据
        return CommonResult.success();
    }
    
}
