package com.dupake.tools.utils;

import com.dupake.tools.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName ConvertUtil
 * @Description 工具转换类
 * @Author dupake
 * @Date 2020/6/5 17:31
 */

@Slf4j
public class ConvertUtil {


    /**
     * @Description: 对象copy
     * @Param: originObj：原对象  obj1：目标对象
     */
    public static Object copyObj(Object originObj, Object targetObj) {
        try {
            Class<?> classType = originObj.getClass();
            Class<?> targetClassType = targetObj.getClass();
            Object resultObj = targetClassType.newInstance();
            //由于不知道源对象的具体属性和属性值，通过反射机制，先得到属性名称，再拼接字段得到属性对应的get,set方法
            for (Field field : classType.getDeclaredFields()) {
                String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                try {
                    Method getMethod = classType.getDeclaredMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(originObj, new Object[]{});
                    Method setMethod = targetClassType.getDeclaredMethod(setMethodName, new Class[]{field.getType()});
                    setMethod.invoke(resultObj, new Object[]{value});
                } catch (NoSuchMethodException noSuchMethodException) {
                    continue;
                }
            }
            return resultObj;
        } catch (Exception e) {
            log.error("ConvertUtil copyObj error:{}", e);
            throw new BadRequestException("转化异常");
        }
    }


}
