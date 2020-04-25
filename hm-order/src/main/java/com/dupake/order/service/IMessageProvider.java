package com.dupake.order.service;

/**
 * @author dupake
 * @version 1.0.0
 * @ClassName IMessageProvider.java
 * @Description 消息生产者
 * @createTime 2020年04月25日 12:06:00
 */
public interface IMessageProvider {
    /**
     * 消息发送
     *
     * @return
     */
    String send();

}
