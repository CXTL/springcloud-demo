package com.dupake.system.service;

import com.dupake.common.pojo.dto.req.user.UserAddRequest;
import com.dupake.common.pojo.dto.req.user.UserQueryRequest;
import com.dupake.common.pojo.dto.req.user.UserUpdateRequest;
import com.dupake.common.pojo.dto.res.UserDTO;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysUser;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    CommonResult<Map<String, Object>> getUserInfo();

    CommonResult<CommonPage<UserDTO>> listByPage(UserQueryRequest userQueryRequest);

    CommonResult addUser(UserAddRequest userAddRequest);

    CommonResult updateUser(UserUpdateRequest userUpdateRequest, HttpServletRequest request);

    CommonResult delete(Long userId, HttpServletRequest request);

    CommonResult deleteBatch(List<Long>  ids);
}
