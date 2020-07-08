//package com.dupake.search.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.dupake.common.message.Result;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author dupake
// * @version 1.0.0
// * @ClassName EsController.java
// * @Description TODO
// * @createTime 2020年05月02日 15:14:00
// */
//@Slf4j
//public class EsController {
//
//
//    private
//
//
//
//
//    /**
//     * @Description 创建Elastic索引
//     * @param idxVo
//     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
//     * @throws
//     * @date 2019/11/19 11:07
//     */
//    @RequestMapping(value = "/createIndex",method = RequestMethod.POST)
//    public Result createIndex(@RequestBody IdxVo idxVo){
//
//        try {
//            //索引不存在，再创建，否则不允许创建
//            if(!baseElasticDao.indexExist(idxVo.getIdxName())){
//                String idxSql = JSONObject.toJSONString(idxVo.getIdxSql());
//                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
//                baseElasticDao.createIndex(idxVo.getIdxName(),idxSql);
//            } else{
//                response.setStatus(false);
//                response.setCode(ResponseCode.DUPLICATEKEY_ERROR_CODE.getCode());
//                response.setMsg("索引已经存在，不允许创建");
//            }
//        } catch (Exception e) {
//            response.setStatus(false);
//            response.setCode(ResponseCode.ERROR.getCode());
//            response.setMsg(ResponseCode.ERROR.getMsg());
//        }
//        return response;
//    }
//
//}
