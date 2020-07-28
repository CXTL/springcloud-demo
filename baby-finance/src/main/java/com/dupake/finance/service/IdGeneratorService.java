package com.dupake.finance.service;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 17:43
 * @description 分布式ID生成接口
 */
public interface IdGeneratorService {
    Long generatorId(String biz);
}
