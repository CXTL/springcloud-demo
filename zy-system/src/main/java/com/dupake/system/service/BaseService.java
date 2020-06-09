package com.dupake.system.service;

import com.dupake.common.constatnts.UserConstant;
import com.dupake.common.dto.res.UserDTO;
import com.dupake.common.message.BaseResult;
import com.dupake.system.utils.RedisUtil;
import com.dupake.tools.exception.BadRequestException;
import com.dupake.tools.utils.ObjectUtil;
import com.dupake.tools.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseService
 * @Description 基本逻辑服务类
 * @Author dupake
 * @Date 2020/6/9 17:09
 */
@Component
@Slf4j
public class BaseService {


    @Value("jwt.prefix")
    String prefix;

    @Value("jwt.time")
    String time;

    @Resource
    RedisUtil redisUtil;

    /**
     * 设置用户信息到 redis
     *
     * @param userDTO
     */
    public void setUsers(UserDTO userDTO, HttpServletRequest req) {
        String token = getToken(req);
        redisUtil.hset(token, token, userDTO, Long.parseLong(time));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public synchronized UserDTO getUsers(HttpServletRequest req) {
        String token = getToken(req);
        try {
            UserDTO dto = (UserDTO) redisUtil.hget(token, token);
            if (!ObjectUtil.isNull(dto)) {
                return dto;
            }
        } catch (Exception e) {
            redisUtil.hdel(token, token);
        }
        return null;
    }


    /**
     * 获取token
     *
     * @return
     */
    private String getToken(HttpServletRequest req) {
        String authHeader = req.getHeader(UserConstant.SYS_TOKEN);
        if (StringUtils.isEmpty(authHeader)) {
            log.error("BaseService getToken is null");
            throw new BadRequestException(BaseResult.UNAUTHORIZED.getCode(),
                    BaseResult.UNAUTHORIZED.getMessage());
        }
        return authHeader.substring(prefix.length());
    }

}
