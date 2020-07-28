package com.dupake.finance.mapper;

import com.dupake.common.pojo.dto.req.asset.AssetQueryRequest;
import com.dupake.finance.entity.FinAsset;
import com.dupake.finance.entity.FinAssetRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 财务-余额记录信息表 Mapper 接口
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
public interface FinAssetRecordMapper extends BaseMapper<FinAssetRecord> {

    int getTotalCount(AssetQueryRequest subjectQueryRequest);

    List<FinAssetRecord> selectListPage(AssetQueryRequest subjectQueryRequest);


    void updateBatch(List<FinAssetRecord> finAssetRecordList);
}
