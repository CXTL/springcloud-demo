package com.dupake.system.event;

import com.dupake.common.dto.res.EmailDTO;

/**
 * @ClassName EmailEvent
 * @Description 邮件事件
 * @Author
 * @Date 2020/6/15 12:26
 */
public class EmailEvent extends BaseEvent<EmailDTO> {

    private static final long serialVersionUID = 8103187726344703089L;

    public EmailEvent(EmailDTO msg) {
        super(msg);
    }

    public EmailEvent(Object source, EmailDTO msg){
        super(source,msg);
    }

}
