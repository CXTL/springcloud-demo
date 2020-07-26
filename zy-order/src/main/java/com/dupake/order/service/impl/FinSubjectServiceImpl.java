package com.dupake.order.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.SubjectDTO;
import com.dupake.order.service.FinSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务-会计科目信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class FinSubjectServiceImpl  implements FinSubjectService {

    @Override
    public CommonResult<CommonPage<SubjectDTO>> listByPage(SubjectQueryRequest aubjectQueryRequest) {
        return null;
    }

    @Override
    public CommonResult addSubject(SubjectAddRequest aubjectAddRequest) {
        return null;
    }

    @Override
    public CommonResult updateSubject(SubjectUpdateRequest aubjectUpdateRequest) {
        return null;
    }

    @Override
    public CommonResult deleteSubject(List<Long> ids) {
        return null;
    }
}
