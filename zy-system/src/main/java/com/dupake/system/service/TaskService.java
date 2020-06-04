package com.dupake.system.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName Task
 * @Description 自定义线程 逻辑类
 * @Author dupake
 * @Date 2020/6/4 12:38
 */
@Slf4j
@Component
public class TaskService {


    @Resource
    private MailService mailService;

    /**
     * 发送邮件
     *
     * @param receiverMailAddress 接受邮箱地址
     * @param subject             标题
     * @param code                验证码
     */
    @Async("taskExecutor")
    public void sendMail(final String receiverMailAddress, String subject, String code) {
        try {
            final StringBuffer sb = new StringBuffer(); //实例化一个StringBuffer
            sb.append("<h2>" + "亲爱的" + receiverMailAddress + "您好！</h2>").append("<p style='text-align: center; font-size: 24px; font-weight: bold'>您的注册验证码为:" + code + "</p>");
            mailService.sendHtmlMail(receiverMailAddress, subject, sb.toString());
        } catch (Exception e) {
            log.error("UserServiceImpl sendMail error , param:{} error:{}", receiverMailAddress, e);
//            throw new BusinessException(BaseErrorCode.ERROR_SYSTEM.getCode(), BaseErrorCode.ERROR_SYSTEM.getMsg());
        }
    }

}
