package com.dupake.finance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.constatnts.FinConstant;
import com.dupake.common.constatnts.RedisKeyConstant;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.SubjectDTO;
import com.dupake.common.pojo.dto.res.system.MenuDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.finance.entity.FinAccount;
import com.dupake.finance.entity.FinSubject;
import com.dupake.finance.exception.BadRequestException;
import com.dupake.finance.mapper.FinSubjectMapper;
import com.dupake.finance.service.BaseService;
import com.dupake.finance.service.FinSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class FinSubjectServiceImpl extends BaseService implements FinSubjectService {


    @Resource
    private FinSubjectMapper finSubjectMapper;

    private final String redisKey = new StringBuffer(RedisKeyConstant.BABY_FINANCE_SUBJECT_KEY).toString();



    /**
     * @param subjectQueryRequest :
     * @return com.dupake.common.message.CommonResult<com.dupake.common.message.CommonPage < com.dupake.common.pojo.dto.res.finance.SubjectDTO>>
     * @Description 分页查询投资列表
     **/
    @Override
    public CommonResult<CommonPage<SubjectDTO>> listByPage(SubjectQueryRequest subjectQueryRequest) {
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        Map<String, String> subjectMap = getSubjectMap();
        int totalCount = finSubjectMapper.getTotalCount(subjectQueryRequest);
        if (totalCount > 0) {
            List<FinSubject> finSubjects = finSubjectMapper.selectListPage(subjectQueryRequest);
            if (!ObjectUtils.isEmpty(finSubjects)) {
                subjectDTOS = finSubjects.stream().map(a -> {
                    SubjectDTO subjectDTO = new SubjectDTO();
                    BeanUtils.copyProperties(a, subjectDTO);
                    subjectDTO.setParentName(Objects.isNull(subjectMap.get(subjectDTO.getParentCode())) ?
                            subjectDTO.getParentCode() : subjectMap.get(subjectDTO.getParentCode()));
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

        try {
            //redis设置科目信息
            Map<String, String> subjectMap = getSubjectMap();
            subjectMap.put(subjectAddRequest.getSubjectCode(),subjectAddRequest.getSubjectName());
            redisUtil.hset(redisKey,redisKey, JSONObject.toJSONString(subjectMap));
        }catch (Exception e){
            redisUtil.hdel(redisKey,redisKey);
            log.error("FinSubjectServiceImpl redis add subject error , param:{}, error:{}", JSONObject.toJSONString(subjectAddRequest), e);
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


        try {
            //redis设置科目信息
            Map<String, String> subjectMap = getSubjectMap();
            subjectMap.put(subjectUpdateRequest.getSubjectCode(),subjectUpdateRequest.getSubjectName());
            redisUtil.hset(redisKey,redisKey, JSONObject.toJSONString(subjectMap));
        }catch (Exception e){
            redisUtil.hdel(redisKey,redisKey);
            log.error("FinSubjectServiceImpl redis update subject error , param:{}, error:{}", JSONObject.toJSONString(subjectUpdateRequest), e);
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
        List<FinSubject> finSubjectList = finSubjectMapper.selectList(new LambdaQueryWrapper<FinSubject>().eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue()).in(FinSubject::getId, ids));
        if(!CollectionUtils.isEmpty(finSubjectList)){

            List<FinSubject> finSubjects = ids.stream().map(a -> {
                FinSubject finSubject = new FinSubject();
                finSubject.setIsDeleted(YesNoSwitchEnum.YES.getValue());
                finSubject.setUpdateTime(DateUtil.getCurrentTimeMillis());
                finSubject.setId(a);
                return finSubject;
            }).collect(Collectors.toList());


            List<String> codes = finSubjectList.stream().map(a -> a.getSubjectCode()).collect(Collectors.toList());
            List<FinSubject> subjects = finSubjectMapper.selectList(new LambdaQueryWrapper<FinSubject>()
                    .eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue()).in(FinSubject::getParentCode, codes));
            if(!CollectionUtils.isEmpty(subjects)){
                log.error("FinSubjectServiceImpl fin_subject_delete_error_exist_sub_subject , param:{}", JSONObject.toJSONString(ids));
                throw new BadRequestException(BaseResult.FIN_SUBJECT_DELETE_ERROR_EXIST_SUB_SUBJECT.getCode(),
                        BaseResult.FIN_SUBJECT_DELETE_ERROR_EXIST_SUB_SUBJECT.getMessage());
            }
            try {
                finSubjectMapper.updateBatch(finSubjects);
            } catch (Exception e) {
                log.error("FinSubjectServiceImpl update subject error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
                throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
            }


            try {
                Map<String, String> subjectMap = getSubjectMap();
                Map<String, String> stringMap = finSubjectList.stream().collect(Collectors.toMap(FinSubject::getSubjectCode, FinSubject::getSubjectName));
                subjectMap.forEach((k,v)->{
                    if(!Objects.isNull(stringMap.get(k))){
                        subjectMap.remove(k);
                    }
                });
                redisUtil.hset(redisKey,redisKey, JSONObject.toJSONString(subjectMap));

            }catch (Exception e){
                redisUtil.hdel(redisKey,redisKey);
                log.error("FinAccountServiceImpl redis del subject error , param:{}, error:{}", JSONObject.toJSONString(ids), e);
            }

        }

        //todo 修改角色投资表数据




        return CommonResult.success();
    }

    @Override
    public CommonResult<List<SubjectDTO>> treeList() {
        List<FinSubject> sysMenuList = finSubjectMapper.selectList(
                new LambdaUpdateWrapper<FinSubject>().eq(FinSubject::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        List<SubjectDTO> subjectDTOS = conventMenu(sysMenuList);

        return CommonResult.success(subjectDTOS);
    }

    /**
     * 递归菜单树
     *
     * @param finSubjectList
     * @return
     */
    private List<SubjectDTO> conventMenu(List<FinSubject> finSubjectList) {
        List<SubjectDTO> subjectDTOS = new ArrayList<>(finSubjectList.size());
        //递归获取权限树
        if (!CollectionUtils.isEmpty(finSubjectList)) {
            subjectDTOS = finSubjectList.stream()
                    .filter(subject -> subject.getParentCode().equals("0"))
                    .map(subject -> covert(subject, finSubjectList)).collect(Collectors.toList());

        }else {
            finSubjectMapper.insert(
                    FinSubject.builder()
                            .subjectCode(FinConstant.FIN_TOP_SUBJECT_CODE)
                            .parentCode(FinConstant.FIN_TOP_SUBJECT_PARENT_CODE)
                            .subjectName(FinConstant.FIN_TOP_SUBJECT_NAME)
                            .build()
            );


            subjectDTOS.add(SubjectDTO.builder()
                    .subjectCode(FinConstant.FIN_TOP_SUBJECT_CODE)
                    .parentCode(FinConstant.FIN_TOP_SUBJECT_PARENT_CODE)
                    .subjectName(FinConstant.FIN_TOP_SUBJECT_NAME)
                    .build());


            Map<String, String> subjectMap = getSubjectMap();
            subjectMap.put(FinConstant.FIN_TOP_SUBJECT_CODE,FinConstant.FIN_TOP_SUBJECT_NAME);
            redisUtil.hset(redisKey,redisKey, JSONObject.toJSONString(subjectMap));
        }
        return subjectDTOS;
    }

    /**
     * 当找不到子级权限的时候map操作不会递归调用
     *
     * @param subject
     * @param finSubjects
     * @return
     */
    private SubjectDTO covert(FinSubject subject, List<FinSubject> finSubjects) {
        SubjectDTO dto = SubjectDTO.builder().build();
        BeanUtils.copyProperties(subject, dto);
        List<SubjectDTO> children = finSubjects.stream()
                .filter(subMenu -> subMenu.getParentCode().equals(subject.getSubjectCode()))
                .map(subMenu -> covert(subMenu, finSubjects)).collect(Collectors.toList());
        dto.setChildren(children);
        return dto;
    }
}
