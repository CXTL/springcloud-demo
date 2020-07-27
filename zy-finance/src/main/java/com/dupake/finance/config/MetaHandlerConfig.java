/**
 * Copyright 2020 Zhejiang Lab. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =============================================================
 */

package com.dupake.finance.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dupake.common.constatnts.StringConstant;
import com.dupake.common.enums.YesNoSwitchEnum;
import com.dupake.common.utils.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 * @date 2020-6-10
 */
@Component
public class MetaHandlerConfig implements MetaObjectHandler {


    private final String LOCK_USER_ID = "LOCK_USER_ID";


    /**
     * 新增数据执行
     *
     * @param metaObject
     */

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.isNull(getFieldValByName(StringConstant.CREATE_TIME, metaObject))) {
            this.setFieldValByName("createTime", DateUtil.getCurrentTimeMillis(), metaObject);
        }
        if (Objects.isNull(getFieldValByName(StringConstant.UPDATE_TIME, metaObject))) {
            this.setFieldValByName("updateTime", DateUtil.getCurrentTimeMillis(), metaObject);
        }
        synchronized (LOCK_USER_ID){
            if (Objects.isNull(getFieldValByName(StringConstant.UPDATE_USER_ID, metaObject))) {
                this.setFieldValByName("updateId", getUserId(), metaObject);
            }
            if (Objects.isNull(getFieldValByName(StringConstant.CREATE_USER_ID, metaObject))) {
                this.setFieldValByName("createId", getUserId(), metaObject);
            }
        }
        if (Objects.isNull(getFieldValByName(StringConstant.DELETED, metaObject))) {
            this.setFieldValByName("isDeleted", YesNoSwitchEnum.NO.getValue(),metaObject);
        }
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.isNull(getFieldValByName(StringConstant.UPDATE_TIME, metaObject))) {
            this.setFieldValByName("updateTime", DateUtil.getCurrentTimeMillis(), metaObject);
        }
        if (Objects.isNull(getFieldValByName(StringConstant.UPDATE_USER_ID, metaObject))) {
            this.setFieldValByName("updateId", getUserId(), metaObject);
        }

    }


    /**
     * 获取用户ID
     *
     * @return
     */
    private Long getUserId() {
       return -1L;
    }
}
