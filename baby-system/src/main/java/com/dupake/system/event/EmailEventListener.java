package com.dupake.system.event;

import com.dupake.common.pojo.dto.res.system.EmailDTO;
import com.dupake.common.message.BaseResult;
import com.dupake.system.exception.BadRequestException;
import com.dupake.system.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName EmailEventListener
 * @Description 事件监听
 * @Author
 * @Date 2020/6/15 11:29
 */
@Component
@Slf4j
public class EmailEventListener {

    @Resource
    private MailService mailService;


    @EventListener
    @Async("taskExecutor")
    public void onApplicationEvent(EmailEvent event) {
        EmailDTO emailDTO = (EmailDTO) event.getSource();
        sendMail(emailDTO.getReceiverMailAddress(), emailDTO.getSubject(), emailDTO.getCode(), emailDTO.getType());
    }


    /**
     * 发送邮件
     *
     * @param receiverMailAddress 接受邮箱地址
     * @param subject             标题
     * @param code                验证码
     */
    public void sendMail(final String receiverMailAddress, String subject, String code, Integer type) {
        try {
            String sentType = type.compareTo(2) == 0 ? "修改邮箱" : "注册";
            final StringBuffer sb = new StringBuffer(); //实例化一个StringBuffer
            sb.append("<h2>" + "亲爱的" + receiverMailAddress + "您好！</h2>")
                    .append("<p style='text-align: center; font-size: 24px; font-weight: bold'>您的" + sentType + "验证码为:" + code + "</p>");
            mailService.sendHtmlMail(receiverMailAddress, subject, sb.toString());
        } catch (Exception e) {
            log.error("EmailEventListener sendMail error , param:{} error:{}", receiverMailAddress, e);
            throw new BadRequestException(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMessage());
        }
    }

}