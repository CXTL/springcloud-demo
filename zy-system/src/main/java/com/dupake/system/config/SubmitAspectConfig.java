package com.dupake.system.config;

import com.dupake.common.annotation.SubmitToken;
import com.dupake.system.utils.CacheUtil;
import com.dupake.tools.exception.BadRequestException;
import com.dupake.tools.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SubmitAspect
 * @Description TODO
 * @Author dupake
 * @Date 2020/6/24 12:14
 */
@Component
@Aspect
public class SubmitAspectConfig {
    /**
     * 其中@Pointcut声明了切点（这里的切点是我们自定义的注解类），
     *
     * @Before声明了通知内容，在具体的通知中，我们通过@annotation(submitToken)拿到了自定义的注解对象， 所以就能够获取我们在使用注解时赋予的值了。
     */

    @Pointcut("@annotation(com.dupake.common.annotation.SubmitToken)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("SubmitToken");
        if(StringUtils.isEmpty(token)){
            throw new BadRequestException("submitToken不能为空！");
        }
        if(!CacheUtil.containKey(token)){
            throw new BadRequestException("submitToken失效，请重新获取！");
        }
        Object value = CacheUtil.getValue(token);
        if(!"false".equals(value)){
            throw new BadRequestException("数据正在处理，请不要重复提交");
        }
        //验证通过之后，将submitToken对应的值设置为正在处理
        CacheUtil.setValue(token, "true");
    }


    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String submitToken = request.getHeader("SubmitToken");
        if (StringUtils.isNotEmpty(submitToken)) {
            CacheUtil.removeCache(submitToken);
        }
    }

}