package com.dupake.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.SubjectDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinSubject;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinSubjectMapper;
import com.dupake.finance.service.FinSubjectService;
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
     * @param subjectQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.finance.SubjectDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<SubjectDTO>> listByPage(SubjectQueryRequest subjectQueryRequest) {
        List<SubjectDTO> subjectDTOS = new ArrayList<>();

        int totalCount = finSubjectMapper.getTotalCount(subjectQueryRequest);
        if (totalCount > 0) {
            List<FinSubject> finSubjects = finSubjectMapper.selectListPage(subjectQueryRequest);
            if (!ObjectUtils.isEmpty(finSubjects)) {
                subjectDTOS = finSubjects.stream().map(a -> {
                    SubjectDTO subjectDTO = new SubjectDTO();
                    BeanUtils.copyProperties(a, subjectDTO);
                    return subjectDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(subjectDTOS, totalCount));
    }


    /**
     * @param subjectAddRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 新增投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addSubject(SubjectAddRequest subjectAddRequest) {
        //投资名称校验 权限标识校验
//        checkSubjectInfo(subjectAddRequest.getName(), subjectAddRequest.getPermission(),null);

        //落地投资数据
        try {
            finSubjectMapper.insert(FinSubject.builder()
                    .borrowFlag(subjectAddRequest.getBorrowFlag())
                    .remark(subjectAddRequest.getRemark())
                    .subjectCode(subjectAddRequest.getSubjectCode())
                    .parentCode(subjectAddRequest.getParentCode())
                    .subjectType(subjectAddRequest.getSubjectType())
                    .subjectName(subjectAddRequest.getSubjectName())
                    .build());
        } catch (Exception e) {
            log.error("FinSubjectServiceImpl add subject error , param:{}, error:{}", JSONObject.toJSONString(subjectAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * @param subjectUpdateRequest :
     * @return com.dupake.common.message.CommonResult
     * @Description 修改投资
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult updateSubject(SubjectUpdateRequest subjectUpdateRequest) {

        FinSubject finSubject = finSubjectMapper.selectOne(new LambdaQueryWrapper<FinSubject>()
                .eq(FinSubject::getId, subjectUpdateRequest.getId())
                .eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue()));
        if(Objects.isNull(finSubject)){
            log.error("subject is null");
            throw new BadRequestException(BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getCode(), BaseResult.SYS_ROLE_INFO_IS_NOT_EXIST.getMessage());
        }
        //投资名称校验 权限标识校验
//        checkSubjectInfo(subjectUpdateRequest.getName(), subjectUpdateRequest.getPermission(),finSubject);

        //修改投资信息
        try {
            finSubjectMapper.updateById(FinSubject.builder()
                    .borrowFlag(subjectUpdateRequest.getBorrowFlag())
                    .remark(subjectUpdateRequest.getRemark())
                    .subjectCode(subjectUpdateRequest.getSubjectCode())
                    .parentCode(subjectUpdateRequest.getParentCode())
                    .subjectType(subjectUpdateRequest.getSubjectType())
                    .subjectName(subjectUpdateRequest.getSubjectName())
                    .id(subjectUpdateRequest.getId())
                    .build());
        } catch (Exception e) {
            log.error("FinSubjectServiceImpl update subject error , param:{}, error:{}", JSONObject.toJSONString(subjectUpdateRequest), e);
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
        //todo  科目下存在数据 无法删除此科目 , 管理员可删除

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
