package com.dupake.finance.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.subject.SubjectAddRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectQueryRequest;
import com.dupake.common.pojo.dto.req.subject.SubjectUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.SubjectDTO;
import com.dupake.common.pojo.dto.res.system.MenuDTO;
import com.dupake.finance.service.FinSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-会计科目信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Api(tags = "财务：科目信息")
@RestController
@RequestMapping("/finance/subject")
public class FinSubjectController {



    @Resource
    private FinSubjectService subjectService;

    @ApiOperation("分页查询科目列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<SubjectDTO>> listByPage(@Valid @RequestBody SubjectQueryRequest subjectQueryRequest) {
        return subjectService.listByPage(subjectQueryRequest);
    }


    @ApiOperation("新增指定科目信息")
    @PostMapping(value = "/addSubject")
    public CommonResult addSubject(@Valid @RequestBody SubjectAddRequest subjectAddRequest) {
        return subjectService.addSubject(subjectAddRequest);
    }



    @ApiOperation("修改指定科目信息")
    @PostMapping(value = "/updateSubject")
    public CommonResult updateSubject(@Valid @RequestBody SubjectUpdateRequest subjectUpdateRequest) {
        return subjectService.updateSubject(subjectUpdateRequest);
    }




    @ApiOperation("批量删除科目信息")
    @PostMapping(value = "/deleteSubject")
    public CommonResult deleteSubject(@RequestParam(value = "ids") List<Long> ids) {
        return subjectService.deleteSubject(ids);
    }


    @ApiOperation(value = "获取科目列表(层级结构)")
    @GetMapping(value = "/treeList")
    public CommonResult<List<SubjectDTO>> treeList() {
        return subjectService.treeList();
    }



}
