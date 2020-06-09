package com.dupake.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.dto.req.user.UserAddRequest;
import com.dupake.common.dto.req.user.UserUpdateRequest;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.enums.UserStatusEnum;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysUser;
import com.dupake.system.mapper.SysUserMapper;
import com.dupake.system.service.BaseService;
import com.dupake.system.service.SysUserService;
import com.dupake.tools.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseService implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 根据名称查询用户信息
     *
     * @param name
     * @return
     */
    @Override
    public SysUser findUserByName(String name) {
        SysUser sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, name)
        );
        return sysUser;
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public CommonResult<UserDTO> getUserInfo(Long userId) {
        SysUser sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getId, userId)
                        .eq(SysUser::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        if (!ObjectUtil.isNull(sysUser)) {
            log.error("SysUserServiceImpl getUserInfo user is not exist userId:{}", userId);
            return CommonResult.failed(BaseResult.SYS_USER_IS_NOT_EXIST.getCode(),
                    BaseResult.SYS_USER_IS_NOT_EXIST.getMessage());
        }
        BeanUtils.copyProperties(sysUser, UserDTO.builder());
        return CommonResult.success();
    }

    /**
     * 分页查询用户列表
     *
     * @param pageable
     * @return
     */
    @Override
    public CommonResult<CommonPage<UserDTO>> listByPage(Pageable pageable) {

        return null;
    }

    /**
     * 用户新增
     *
     * @param userAddRequest
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult add(UserAddRequest userAddRequest, HttpServletRequest request) {


        //唯一性校验
        CommonResult commonResult = checkUserInfo(
                userAddRequest.getUsername(), userAddRequest.getEmail(), userAddRequest.getPhone());
        if (BaseResult.SUCCESS.getCode().compareTo(commonResult.getCode()) != 0) {
            return commonResult;
        }
        SysUser sysUser = null;

        try {
            //落地用户信息
            UserDTO dto = super.getUsers(request);
            int insert = sysUserMapper.insert(SysUser.builder()
                    .createId(dto.getId())
                    .createTime(System.currentTimeMillis())
                    .updateId(dto.getId())
                    .updateTime(System.currentTimeMillis())
                    .isDeleted(YesNoSwitchEnum.NO.getValue())
                    .email(userAddRequest.getEmail())
                    .username(userAddRequest.getUsername())
                    .password(userAddRequest.getPassword())
                    .status(userAddRequest.getStatus())
                    .sex(userAddRequest.getSex())
                    .phone(userAddRequest.getPhone()).build());
        } catch (Exception e) {
            log.error("SysUserServiceImpl add user error , param:{}, error:{}", JSONObject.toJSONString(userAddRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult update(UserUpdateRequest userUpdateRequest, HttpServletRequest request) {

        //校验用户是否存在
        SysUser sysUser = sysUserMapper.selectById(userUpdateRequest.getId());
        if (ObjectUtil.isNull(sysUser)) {
            log.error("user is not exist");
            return CommonResult.failed(BaseResult.SYS_USER_IS_NOT_EXIST.getCode(),
                    BaseResult.SYS_USER_IS_NOT_EXIST.getMessage());
        }
        //校验用户状态
        if (UserStatusEnum.ACTIVATED.getValue().compareTo(sysUser.getStatus()) != 0) {
            throw new BadRequestException(BaseResult.SYS_USERNAME_PASSWORD_ERROR.getCode(),
                    "账号状态" + UserStatusEnum.getEnumValue(sysUser.getStatus()).getDesc() + ",请联系管理员");
        }

        //todo 校验 商户只能修改属于自己的商户子账号和代理的信息

        //唯一性校验
        CommonResult commonResult = checkUserInfo(
                userUpdateRequest.getUsername(), userUpdateRequest.getEmail(), userUpdateRequest.getPhone());
        if (BaseResult.SUCCESS.getCode().compareTo(commonResult.getCode()) != 0) {
            return commonResult;
        }
        try {
            //修改用户信息
            int i = sysUserMapper.updateById(SysUser.builder()
                    .updateId(super.getUsers(request).getId())
                    .updateTime(System.currentTimeMillis())
                    .phone(userUpdateRequest.getPhone())
                    .email(userUpdateRequest.getEmail())
                    .username(userUpdateRequest.getUsername())
                    .sex(userUpdateRequest.getSex())
                    .status(userUpdateRequest.getStatus())
                    .password(userUpdateRequest.getPassword()).build());
        } catch (Exception e) {
            log.error("SysUserServiceImpl update user error , param:{}, error:{}", JSONObject.toJSONString(userUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }

    /**
     * 刪除用戶
     *
     * @param userId
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult delete(Long userId, HttpServletRequest request) {
        //校验用户是否存在
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (ObjectUtil.isNull(sysUser)) {
            log.error("user is not exist");
            return CommonResult.failed(BaseResult.SYS_USER_IS_NOT_EXIST.getCode(),
                    BaseResult.SYS_USER_IS_NOT_EXIST.getMessage());
        }

        //todo 校验 商户只能刪除属于自己的商户子账号和代理的信息

        try {
            sysUserMapper.updateById(SysUser.builder()
                    .id(userId)
                    .updateId(super.getUsers(request).getId())
                    .updateTime(System.currentTimeMillis())
                    .isDeleted(YesNoSwitchEnum.YES.getValue()).build());
        } catch (Exception e) {
            log.error("SysUserServiceImpl delete user error , param:{}, error:{}", userId, e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
        return null;
    }

    /**
     * 用户唯一性校验
     *
     * @param username
     * @param email
     * @param phone
     * @return
     */
    private CommonResult checkUserInfo(String username, String email, String phone) {
        SysUser sysUser;

        //账号唯一性校验
        sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getUsername, username)
                        .eq(SysUser::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        if (!ObjectUtil.isNull(sysUser)) {
            log.error("SysUserServiceImpl getUserInfo username is  exist param:{}", username);
            return CommonResult.failed(BaseResult.SYS_USERNAME_IS_EXIST.getCode(),
                    BaseResult.SYS_USERNAME_IS_EXIST.getMessage());
        }
        //邮箱唯一性校验
        sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getEmail, email)
                        .eq(SysUser::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        if (!ObjectUtil.isNull(sysUser)) {
            log.error("SysUserServiceImpl getUserInfo email is  exist param:{}", email);
            return CommonResult.failed(BaseResult.SYS_EMAIL_IS_EXIST.getCode(),
                    BaseResult.SYS_EMAIL_IS_EXIST.getMessage());
        }
        //手机号校验
        sysUser = sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>()
                        .eq(SysUser::getPhone, phone)
                        .eq(SysUser::getIsDeleted, YesNoSwitchEnum.NO.getValue())
        );
        if (ObjectUtil.isNull(sysUser)) {
            log.error("SysUserServiceImpl getUserInfo email is  exist param:{}", phone);
            return CommonResult.failed(BaseResult.SYS_PHONE_IS_EXIST.getCode(),
                    BaseResult.SYS_PHONE_IS_EXIST.getMessage());
        }
        return CommonResult.success();
    }


}
