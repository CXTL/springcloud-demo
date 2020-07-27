package com.dupake.finance.mapper;

import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.finance.entity.FinAsset;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 财务-余额信息表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinAssetMapper extends BaseMapper<FinAsset> {


    void updateBatch(List<FinAsset> finAssets);
}
