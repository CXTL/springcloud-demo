package com.dupake.finance.service.impl;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.asset.AssetAddRequest;
import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.common.pojo.dto.req.asset.AssetUpdateRequest;
import com.dupake.common.pojo.dto.res.AssetDTO;
import com.dupake.finance.service.FinAssetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 财务-余额信息表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class FinAssetServiceImpl implements FinAssetService {

    @Override
    public CommonResult<CommonPage<AssetDTO>> listByPage(AssetQueryRequest assetQueryRequest) {
        return null;
    }

    @Override
    public CommonResult addBalance(AssetAddRequest assetAddRequest) {
        return null;
    }

    @Override
    public CommonResult updateBalance(AssetUpdateRequest assetUpdateRequest) {
        return null;
    }

    @Override
    public CommonResult deleteBalance(List<Long> ids) {
        return null;
    }
}
