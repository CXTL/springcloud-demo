package com.dupake.system.service;

import com.dupake.common.dto.req.user.UserAddRequest;
import com.dupake.common.dto.req.user.UserUpdateRequest;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysUser;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysUserService {

    SysUser findUserByName(String name);

    CommonResult<UserDTO> getUserInfo(Long userId);

    CommonResult<CommonPage<UserDTO>> listByPage(Pageable pageable);

    CommonResult add(UserAddRequest userAddRequest, HttpServletRequest request);

    CommonResult update(UserUpdateRequest userUpdateRequest, HttpServletRequest request);

    CommonResult delete(Long userId, HttpServletRequest request);
}
