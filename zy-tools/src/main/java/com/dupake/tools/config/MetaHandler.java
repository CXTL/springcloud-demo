package com.dupake.tools.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.tools.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 * @Author dupake
 * @Date 2020/6/10 10:12
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {


    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.isNull(getFieldValByName("createTime", metaObject))) {
            this.setFieldValByName("createTime", DateUtil.getCurrentTimestamp(), metaObject);
        }
        if (Objects.isNull(getFieldValByName("updateTime", metaObject))) {
            this.setFieldValByName("updateTime", DateUtil.getCurrentTimestamp(), metaObject);
        }
        if (Objects.isNull(getFieldValByName("updateUserId", metaObject))) {
            this.setFieldValByName("updateUserId", getUserId(), metaObject);
        }
        if (Objects.isNull(getFieldValByName("createUserId", metaObject))) {
            this.setFieldValByName("createUserId", getUserId(), metaObject);
        }
        if (Objects.isNull(getFieldValByName("deleted", metaObject))) {
            this.setFieldValByName("deleted", YesNoSwitchEnum.NO.getValue(), metaObject);
        }
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {


        if (Objects.isNull(getFieldValByName("updateTime", metaObject))) {
            this.setFieldValByName("updateTime", DateUtil.getCurrentTimestamp(), metaObject);
        }
        if (Objects.isNull(getFieldValByName("updateId", metaObject))) {
            this.setFieldValByName("updateId", getUserId(), metaObject);
        }

    }


    /**
     * 获取用户ID
     *
     * @return
     */
    private synchronized Long getUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        if (!Objects.isNull(token)) {

        }
        return -1L;
    }
}
