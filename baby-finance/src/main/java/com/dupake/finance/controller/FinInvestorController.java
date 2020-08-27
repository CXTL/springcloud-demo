package com.dupake.finance.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.investor.InvestorAddRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorQueryRequest;
import com.dupake.common.pojo.dto.req.investor.InvestorUpdateRequest;
import com.dupake.common.pojo.dto.res.finance.InvestorDTO;
import com.dupake.finance.service.FinInvestorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-投资人信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-08-27
 */
@Api(tags = "财务：投资人信息")
@RestController
@RequestMapping("/finance/investor")
public class FinInvestorController {

    @Resource
    private FinInvestorService finInvestorService;

    @ApiOperation("分页查询投资人列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<InvestorDTO>> listByPage(@Valid @RequestBody InvestorQueryRequest investorQueryRequest) {
        return finInvestorService.listByPage(investorQueryRequest);
    }


    @ApiOperation("新增指定投资人信息")
    @PostMapping(value = "/addInvestor")
    public CommonResult addInvestor(@Valid @RequestBody InvestorAddRequest investorAddRequest) {
        return finInvestorService.addInvestor(investorAddRequest);
    }



    @ApiOperation("修改指定投资人信息")
    @PostMapping(value = "/updateInvestor")
    public CommonResult updateInvestor(@Valid @RequestBody InvestorUpdateRequest investorUpdateRequest) {
        return finInvestorService.updateInvestor(investorUpdateRequest);
    }




    @ApiOperation("批量删除投资人信息")
    @PostMapping(value = "/deleteInvestor")
    public CommonResult deleteInvestor(@RequestParam(value = "ids") List<Long> ids) {
        return finInvestorService.deleteInvestor(ids);
    }

}
