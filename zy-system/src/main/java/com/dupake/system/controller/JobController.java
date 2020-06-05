/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.system.controller;

import com.dupake.common.annotation.Log;
import com.dupake.system.config.DataScope;
import com.dupake.system.entity.Job;
import com.dupake.system.entity.dto.JobDto;
import com.dupake.system.entity.dto.JobQueryCriteria;
import com.dupake.system.service.JobService;
import com.dupake.tools.exception.BadRequestException;
import com.dupake.tools.service.IGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
* @author dupake
* @date 2019-03-29
*/
@Api(tags = "系统：岗位管理")
@RestController
@RequestMapping("/api/job")
public class JobController {

    @Resource
    private  JobService jobService;

    @Resource
    private DataScope dataScope;

    @Resource
    private IGenerator generator;

    private static final String ENTITY_NAME = "job";



    @Log("导出岗位数据")
    @ApiOperation("导出岗位数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','job:list')")
    public void download(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        jobService.download(generator.convert(jobService.queryAll(criteria), JobDto.class), response);
    }

    @Log("查询岗位")
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("@el.check('admin','job:list','user:list')")
    public ResponseEntity<Object> getJobs(JobQueryCriteria criteria, Pageable pageable){
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        return new ResponseEntity<>(jobService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增岗位")
    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("@el.check('admin','job:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Job resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity<>(jobService.save(resources), HttpStatus.CREATED);
    }

    @Log("修改岗位")
    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("@el.check('admin','job:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Job resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        jobService.saveOrUpdate(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除岗位")
    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("@el.check('admin','job:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        try {
            jobService.removeByIds(ids);
        }catch (Throwable e){
            throw new BadRequestException( "所选岗位存在用户关联，请取消关联后再试");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
