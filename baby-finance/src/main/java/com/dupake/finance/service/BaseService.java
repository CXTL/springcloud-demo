package com.dupake.finance.service;

import com.alibaba.fastjson.JSONObject;
import com.dupake.common.constatnts.RedisKeyConstant;
import com.dupake.finance.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName BaseService
 * @Description 基本逻辑服务类
 * @Author dupake
 * @Date 2020/6/9 17:09
 */
@Component
@Slf4j
public class BaseService {


    @Resource
    public RedisUtil redisUtil;

    /**
     * 获取帐套map数据
     * @return
     */
    public Map<String,String> getAccountMap(){
        Map<String, String> accountMap = new HashMap<>();
        String redisKey = new StringBuffer(RedisKeyConstant.BABY_FINANCE_ACCOUNT_KEY).toString();
        Object obj = redisUtil.hget(redisKey, redisKey);
        if(!Objects.isNull(obj)){
            accountMap = JSONObject.parseObject((String)obj,Map.class);
        }
        return accountMap;
    }

    /**
     * 获取科目map数据
     * @return
     */
    public Map<String,String> getSubjectMap(){
        Map<String, String> subjectMap = new HashMap<>();
        String redisKey = new StringBuffer(RedisKeyConstant.BABY_FINANCE_SUBJECT_KEY).toString();
        Object obj = redisUtil.hget(redisKey, redisKey);
        if(!Objects.isNull(obj)){
            subjectMap = JSONObject.parseObject((String)obj,Map.class);
        }
        return subjectMap;
    }


}
