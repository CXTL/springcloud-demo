package com.dupake.system.service;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.dupake.common.constatnts.UserConstant;
import com.dupake.common.dto.req.LoginRequest;
import com.dupake.common.dto.res.EmailDTO;
import com.dupake.common.dto.res.MenuDTO;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.enums.UserStatusEnum;
import com.dupake.common.message.BaseResult;
import com.dupake.common.message.CommonResult;
import com.dupake.system.entity.SysUser;
import com.dupake.system.event.EmailEventPublisher;
import com.dupake.system.security.JwtTokenUtil;
import com.dupake.system.security.UserDetailsServiceImpl;
import com.dupake.tools.exception.BadRequestException;
import com.dupake.tools.utils.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class LoginServiceImpl extends BaseService implements LoginService {

    @Resource
    SysUserService userService;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.prefix}")
    String prefix;

    @Resource
    EmailEventPublisher publisher;

    @Value("${rsa.private_key}")
    String privateKey;

    @Value("${rsa.public_key}")
    String publicKey;


    /**
     * 登录
     *
     * @param loginRequest
     * @param req
     * @return
     */
    @Override
    public CommonResult<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest req) {


        Map<String, Object> map = null;
        try {
            SysUser dbUser = this.findUserByName(loginRequest.getUsername());

            String decryptPassword = RSAEncrypt.decrypt(loginRequest.getPassword(), privateKey);

            String admin = RSAEncrypt.encrypt("admin", publicKey);
            String decrypt = RSAEncrypt.decrypt(admin, privateKey);
            System.out.println(admin);
            System.out.println(decrypt);

            String dbPassword = RSAEncrypt.decrypt(dbUser.getPassword(), privateKey);


            // 用户不存在 或者 密码错误
            if (dbUser == null || !dbUser.getUsername().equals(loginRequest.getUsername())
                    || !decryptPassword.equals(dbPassword)) {
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
            map = new HashMap<>(3);

            //查询用户权限 todo redis优化
            List<MenuDTO> menus = sysMenuService.listByUserId(dbUser.getId());

            map.put(UserConstant.SYS_TOKEN, prefix.concat(token));
            map.put(UserConstant.SYS_NAME, loginRequest.getUsername());
            map.put(UserConstant.SYS_MENU, menus);


            long l = Long.parseLong(time);
            //将用户信息 存入redis
            redisUtil.hset(token, token, UserDTO.builder()
                    .id(dbUser.getId())
                    .username(dbUser.getUsername())
                    .email(dbUser.getEmail()).build(), l);
        } catch (BadRequestException e) {
            log.error("LoginServiceImpl login error, param:{} error:{}", JSONObject.toJSONString(loginRequest),e);
            throw new BadRequestException(e.getCode(),e.getMessage());
        } catch (UsernameNotFoundException e) {
            log.error("LoginServiceImpl login error, param:{} error:{}", JSONObject.toJSONString(loginRequest),e);
            throw new BadRequestException(BaseResult.FAILED.getCode(),BaseResult.FAILED.getMessage());
        } catch (NumberFormatException e) {
            log.error("LoginServiceImpl login error, param:{} error:{}", JSONObject.toJSONString(loginRequest),e);
            throw new BadRequestException(BaseResult.FAILED.getCode(),BaseResult.FAILED.getMessage());
        }catch (Exception e) {
            log.error("LoginServiceImpl login error, param:{} error:{}", JSONObject.toJSONString(loginRequest),e);
            throw new BadRequestException(BaseResult.FAILED.getCode(),BaseResult.FAILED.getMessage());
        }

        return CommonResult.success(map);

    }

    /**
     * 根据用户名查询用户
     */
    public SysUser findUserByName(String name) {
        return userService.findUserByName(name);
    }


    /**
     * 发送邮箱验证码
     *
     * @param email
     * @return
     */
    @Override
    public CommonResult sentEmailCode(String email) {
        String code = RandomUtil.randomNumbers(6);//产生随机的验证码

        //异步发送邮件
        publisher.sentEmailEvent(
                EmailDTO.builder()
                        .code(code)
                        .subject(1 == 0 ? UserConstant.REGISTER_CODE :
                                UserConstant.EMAIL_UPDATE_CODE)
                        .type(1)
                        .receiverMailAddress(email).build());

        return CommonResult.success();
    }


}
