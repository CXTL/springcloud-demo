package com.dupake.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.SubjectDTO;
import com.dupake.common.pojo.dto.res.SubjectDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.order.entity.FinSubject;
import com.dupake.order.exception.BadRequestException;
import com.dupake.order.mapper.FinSubjectMapper;
import com.dupake.order.service.FinSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 财务-会计科目信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
@Slf4j
public class FinSubjectServiceImpl  implements FinSubjectService {


    @Resource
    private FinSubjectMapper finSubjectMapper;



    /**
     * @param investQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.SubjectDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<SubjectDTO>> listByPage(SubjectQueryRequest investQueryRequest) {
        List<SubjectDTO> investDTOS = new ArrayList<>();

        int totalCount = finSubjectMapper.getTotalCount(investQueryRequest);
        if (totalCount > 0) {
            List<FinSubject> finSubjects = finSubjectMapper.selectListPage(investQueryRequest);
            if (!ObjectUtils.isEmpty(finSubjects)) {
                investDTOS = finSubjects.stream().map(a -> {
                    SubjectDTO investDTO = new SubjectDTO();
                    BeanUtils.copyProperties(a, investDTO);
                    return investDTO;
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
    public CommonResult addSubject(SubjectAddRequest investAddRequest) {
        //投资名称校验 权限标识校验
//        checkSubjectInfo(investAddRequest.getName(), investAddRequest.getPermission(),null);

        //落地投资数据
        try {
            finSubjectMapper.insert(FinSubject.builder()
                    .borrowFlag(investAddRequest.getBorrowFlag())
                    .companyName(investAddRequest.getCompanyName())

                    .build());
        } catch (Exception e) {
            log.error("FinSubjectServiceImpl add subject error , param:{}, error:{}", JSONObject.toJSONString(investAddRequest), e);
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
    public CommonResult updateSubject(SubjectUpdateRequest investUpdateRequest) {

        FinSubject finSubject = finSubjectMapper.selectOne(new LambdaQueryWrapper<FinSubject>()
                .eq(FinSubject::getId, investUpdateRequest.getId())
                .eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(finSubject)){
            log.error("subject is null");
            throw new BadRequestException(BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getCode(), BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getMessage());
        }
        //投资名称校验 权限标识校验
//        checkSubjectInfo(investUpdateRequest.getName(), investUpdateRequest.getPermission(),finSubject);

        //修改投资信息
        try {
            finSubjectMapper.updateById(FinSubject.builder()
                    .borrowFlag(investUpdateRequest.getBorrowFlag())
                    .companyName(investUpdateRequest.getCompanyName())
                    .remark(investUpdateRequest.getRemark())
                    .build());
        } catch (Exception e) {
            log.error("FinSubjectServiceImpl update subject error , param:{}, error:{}", JSONObject.toJSONString(investUpdateRequest), e);
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
    public CommonResult deleteSubject(List<Long> ids) {
        //查询投资及所有子投资
        List<FinSubject> finSubjects = finSubjectMapper.selectList(
                new LambdaUpdateWrapper<FinSubject>()
                        .eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        if (!CollectionUtil.isEmpty(finSubjects)) {
            log.error("subject has sub invests");
            throw new BadRequestException(BaseResult.SYS_MENU_DELETE_ERROR_EXIST_SUB_MENU.getCode(),
                    BaseResult.SYS_MENU_DELETE_ERROR_EXIST_SUB_MENU.getMessage());
        }
        //批量修改投资状态
        List<FinSubject> finSubjectList = ids.stream().map(a -> {
            FinSubject finSubject = new FinSubject();
            finSubject.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            finSubject.setUpdateTime(DateUtil.getCurrentTimeMillis());
            finSubject.setId(a);
            return finSubject;
        }).collect(Collectors.toList());

        try {
            finSubjectMapper.updateBatch(finSubjectList);
        } catch (Exception e) {
            log.error("FinSubjectServiceImpl update subject error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        //todo 修改角色投资表数据
        return CommonResult.success();
    }
}
