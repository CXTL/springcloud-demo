package com.dupake.finance.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.SubjectDTO;

import java.util.List;

/**
 * <p>
 * 财务-会计科目信息表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinSubjectService  {

    CommonResult<CommonPage<SubjectDTO>> listByPage(SubjectQueryRequest aubjectQueryRequest);

    CommonResult addSubject(SubjectAddRequest aubjectAddRequest);

    CommonResult updateSubject(SubjectUpdateRequest aubjectUpdateRequest);

    CommonResult deleteSubject(List<Long> ids);
}
