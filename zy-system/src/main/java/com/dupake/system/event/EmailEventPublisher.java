package com.dupake.system.event;

import com.alibaba.fastjson.JSONObject;
import com.dupake.common.dto.res.EmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName EmailEventPublisher
 * @Description 定义事件发布者
 * @Author
 * @Date 2020/6/15 11:29
 */
@Service
@Slf4j
public class EmailEventPublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 邮件发送事件
     * @param dto
     */
    @Async("taskExecutor")
    public void sentEmailEvent(final EmailDTO dto) {
        try {
            EmailEvent emailEvent = new EmailEvent(dto);
            applicationEventPublisher.publishEvent(emailEvent);
        } catch (Exception e) {
            log.error("EmailEventPublisher sentEmailEvent error , param:{} error:{}", JSONObject.toJSONString(dto), e);
        }
    }
}