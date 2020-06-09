package com.dupake.system.service;

import com.dupake.common.constatnts.UserConstant;
import com.dupake.common.dto.req.LoginRequest;
import com.dupake.common.dto.res.MenuDTO;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.enums.UserStatusEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysUser;
import com.dupake.system.security.JwtTokenUtil;
import com.dupake.system.security.UserDetailsServiceImpl;
import com.dupake.system.utils.MD5Util;
import com.dupake.tools.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginService
 * @Description 登录 逻辑类
 * @Author dupake
 * @Date 2020/5/25 11:18
 */


@Service
@Slf4j
public class LoginService extends BaseService{

    @Resource
    SysUserService userService;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    UserDetailsServiceImpl userDetailsService;

    @Value("jwt.prefix")
    String prefix;


    public CommonResult<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest req){
        SysUser dbUser = this.findUserByName(loginRequest.getUsername());

        // 用户不存在 或者 密码错误
        if (dbUser == null || !dbUser.getUsername().equals(loginRequest.getUsername())
                || !MD5Util.string2MD5(loginRequest.getPassword()).equals(dbUser.getPassword())) {
            throw new BadRequestException(BaseResult.SYS_USERNAME_PASSWORD_ERROR.getCode(),
                    BaseResult.SYS_USERNAME_PASSWORD_ERROR.getMessage());
        }

        // 账号状态异常
        if (UserStatusEnum.ACTIVATED.getValue().compareTo(dbUser.getStatus()) != 0) {
            throw new BadRequestException(BaseResult.SYS_USERNAME_PASSWORD_ERROR.getCode(),
                    "账号状态" + UserStatusEnum.getEnumValue(dbUser.getStatus()).getDesc() + ",请联系管理员");
        }

        // 用户名 密码匹配，获取用户详细信息（包含角色Role）
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // 根据用户详细信息生成token
        final String token = JwtTokenUtil.createToken(loginRequest.getUsername(), userDetails.getAuthorities().toString());
        Map<String, Object> map = new HashMap<>(3);

        //查询用户权限 todo redis优化
        List<MenuDTO> menus = sysMenuService.listByUserId(dbUser.getId());

        map.put(UserConstant.SYS_TOKEN, prefix.concat(token));
        map.put(UserConstant.SYS_NAME, loginRequest.getUsername());
        map.put(UserConstant.SYS_MENU, menus);

       //将用户信息 存入redis
        super.setUsers(
                UserDTO.builder()
                        .id(dbUser.getId())
                        .username(dbUser.getUsername())
                        .email(dbUser.getEmail()).build()
                ,req);

        return CommonResult.success(map);

    }

    /**
     * 根据用户名查询用户
     */
    public SysUser findUserByName(String name) {
        return userService.findUserByName(name);
    }


}
