package com.dupake.finance.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.asset.AssetAddRequest;
import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.common.pojo.dto.req.asset.AssetUpdateRequest;
import com.dupake.common.pojo.dto.res.AssetDTO;
import com.dupake.finance.service.FinAssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 财务-余额信息表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */

@Api(tags = "财务：资产信息")
@RestController
@RequestMapping("/finance/asset")
public class FinAssetController {


    @Resource
    private FinAssetService balanceService;

    @ApiOperation("分页查询帐套列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<AssetDTO>> listByPage(@Valid @RequestBody AssetQueryRequest assetQueryRequest) {
        return balanceService.listByPage(assetQueryRequest);
    }


    @ApiOperation("新增指定帐套信息")
    @PostMapping(value = "/addAsset")
    public CommonResult addAsset(@Valid @RequestBody AssetAddRequest assetAddRequest) {
        return balanceService.addAsset(assetAddRequest);
    }



    @ApiOperation("修改指定帐套信息")
    @PostMapping(value = "/updateAsset")
    public CommonResult updateAsset(@Valid @RequestBody AssetUpdateRequest assetUpdateRequest) {
        return balanceService.updateAsset(assetUpdateRequest);
    }




    @ApiOperation("批量删除帐套信息")
    @PostMapping(value = "/deleteAsset")
    public CommonResult deleteAsset(@RequestParam(value = "ids") List<Long> ids) {
        return balanceService.deleteAsset(ids);
    }
    
}
