/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.dupake.system.service.impl;

import com.dupake.system.entity.RolesMenus;
import com.dupake.system.mapper.RolesMenusMapper;
import com.dupake.system.service.RolesMenusService;
import com.dupake.tools.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
* @author dupake
* @date 2020-05-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "rolesMenus")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RolesMenusServiceImpl extends BaseServiceImpl<RolesMenusMapper, RolesMenus> implements RolesMenusService {

}
