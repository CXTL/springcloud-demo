package com.dupake.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dupake.common.constatnts.UserConstant;
import com.dupake.common.enums.UserStatusEnum;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.system.exception.BadRequestException;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.user.UserAddRequest;
import com.dupake.common.pojo.dto.req.user.UserQueryRequest;
import com.dupake.common.pojo.dto.req.user.UserUpdateRequest;
import com.dupake.common.pojo.dto.res.system.UserDTO;
import com.dupake.common.utils.DateUtil;
import com.dupake.system.entity.SysUser;
import com.dupake.system.mapper.SysUserMapper;
import com.dupake.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysMenuService sysMenuService;


    @Resource
    private SysUserAccountService sysUserAccountService;

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
     * @return
     */
    @Override
    public CommonResult<Map<String, Object>> getUserInfo() {
//
//        UserDTO users = super.getUsers(request);
//        if (ObjectUtil.isNull(users)) {
//            log.error("users is null");
//            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
//        }
//
//        SysUser sysUser = sysUserMapper.selectOne(
//                new LambdaQueryWrapper<SysUser>()
//                        .eq(SysUser::getId, users.getId())
//                        .eq(SysUser::getIsDeleted, YesNoSwitchEnum.NO.getValue())
//        );
//        if (!ObjectUtil.isNull(sysUser)) {
//            log.error("SysUserServiceImpl getUserInfo user is not exist userId:{}", users.getId());
//            return CommonResult.failed(BaseResult.SYS_USER_IS_NOT_EXIST.getCode(),
//                    BaseResult.SYS_USER_IS_NOT_EXIST.getMessage());
//        }
//        BeanUtils.copyProperties(sysUser, UserDTO.builder());

        //todo token获取用户信息
        SysUser sysUser = SysUser.builder().username("test").id(1L).build();

        Map<String, Object> data = new HashMap<>();
        data.put("username", sysUser.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", sysMenuService.getMenuListByUserId(sysUser.getId()));
        data.put("icon", "http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg");

        return CommonResult.success(data);
    }

    /**
     * 分页查询用户列表
     * 指定页数 和每页显示数量 将此句话放入查询前面 IPage<MchBasePermission> page = new Page<>(pageNum, pageSize);
     * 总条数  userIPage.getTotal()
     * 当前页数 userIPage.getCurrent()
     * 当前每页显示数 userIPage.getSize()
     *
     * @param userQueryRequest
     * @return
     */
    @Override
    public CommonResult<CommonPage<UserDTO>> listByPage(UserQueryRequest userQueryRequest) {

        List<UserDTO> userDTOList = new ArrayList<>();

        int totalCount = sysUserMapper.getTotalCount(userQueryRequest);
        if(totalCount>0){
            List<SysUser> sysUserIPage = sysUserMapper.selectUserListPage(userQueryRequest);
            if (!ObjectUtils.isEmpty(sysUserIPage)) {
                userDTOList = sysUserIPage.stream().map(a -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(a, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());
            }
        }
        return CommonResult.success(CommonPage.restPage(userDTOList,totalCount));
    }

    /**
     * 用户新增
     *
     * @param userAddRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addUser(UserAddRequest userAddRequest) {


        //唯一性校验
        CommonResult commonResult = checkUserInfo(
                userAddRequest.getUsername(), userAddRequest.getEmail(), userAddRequest.getPhone());
        if (BaseResult.SUCCESS.getCode().compareTo(commonResult.getCode()) != 0) {
            return commonResult;
        }
        SysUser sysUser = null;

        try {
            //落地用户信息
            int insert = sysUserMapper.insert(SysUser.builder()
                    .email(userAddRequest.getEmail())
                    .username(userAddRequest.getUsername())
                    .password(userAddRequest.getPassword())
                    .status(userAddRequest.getStatus())
                    .nickName(userAddRequest.getNickName())
                    .remark(userAddRequest.getRemark())
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
    public CommonResult updateUser(UserUpdateRequest userUpdateRequest) {

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
                    .phone(userUpdateRequest.getPhone())
                    .email(userUpdateRequest.getEmail())
                    .username(userUpdateRequest.getUsername())
                    .status(userUpdateRequest.getStatus())
                    .remark(userUpdateRequest.getRemark())
                    .nickName(userUpdateRequest.getNickName())
                    .password(userUpdateRequest.getPassword()).build());
        } catch (Exception e) {
            log.error("SysUserServiceImpl update user error , param:{}, error:{}", JSONObject.toJSONString(userUpdateRequest), e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }

        return CommonResult.success();
    }


    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult deleteUser(List<Long> ids) {
        Long adminId = Long.valueOf(UserConstant.ADMIN_USER_ID);
        if (ids.contains(adminId)) {
            return CommonResult.failed(BaseResult.SYS_USER_CANNOT_DELETE.getCode(),
                    BaseResult.SYS_USER_CANNOT_DELETE.getMessage());
        }

        List<SysUser> sysUsers = ids.stream().map(a -> {
            SysUser sysUser = SysUser.builder().build();
            sysUser.setId(a);
            sysUser.setIsDeleted(YesNoSwitchEnum.YES.getValue());
            sysUser.setUpdateTime(DateUtil.getCurrentTimeMillis());
            return sysUser;
        }).collect(Collectors.toList());

        sysUserMapper.updateBatch(sysUsers);

        return CommonResult.success();
    }


    /**
     * 分配用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public CommonResult allocRole(Long userId, List<Long> roleIds) {
        return sysUserRoleService.allocRole(userId,roleIds);
    }

    /**
     * 分配用户帐套信息
     * @param userId
     * @param accountCodes
     * @return
     */
    @Override
    public CommonResult allocAccount(Long userId, List<String> accountCodes) {
        return sysUserAccountService.allocAccount(userId,accountCodes);
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
        if (!ObjectUtil.isNull(sysUser)) {
            log.error("SysUserServiceImpl getUserInfo email is  exist param:{}", phone);
            return CommonResult.failed(BaseResult.SYS_PHONE_IS_EXIST.getCode(),
                    BaseResult.SYS_PHONE_IS_EXIST.getMessage());
        }
        return CommonResult.success();
    }


}
