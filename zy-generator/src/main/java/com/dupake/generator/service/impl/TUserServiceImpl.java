package com.dupake.generator.service.impl;

import com.dupake.generator.entity.TUser;
import com.dupake.generator.dao.TUserMapper;
import com.dupake.generator.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-05-17
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
