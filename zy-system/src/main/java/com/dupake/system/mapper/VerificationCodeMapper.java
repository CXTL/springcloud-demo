/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package com.dupake.system.mapper;


import com.dupake.system.entity.VerificationCode;
import com.dupake.tools.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface VerificationCodeMapper extends CoreMapper<VerificationCode> {

}
