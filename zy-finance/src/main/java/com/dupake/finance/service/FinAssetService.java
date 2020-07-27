package com.dupake.finance.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.asset.AssetAddRequest;
import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.common.pojo.dto.req.asset.AssetUpdateRequest;
import com.dupake.common.pojo.dto.res.AssetDTO;

import java.util.List;

/**
 * <p>
 * 财务-余额信息表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinAssetService {

    CommonResult<CommonPage<AssetDTO>> listByPage(AssetQueryRequest assetQueryRequest);

    CommonResult addBalance(AssetAddRequest assetAddRequest);

    CommonResult updateBalance(AssetUpdateRequest assetUpdateRequest);

    CommonResult deleteBalance(List<Long> ids);
}
