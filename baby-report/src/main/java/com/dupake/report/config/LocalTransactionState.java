package com.dupake.report.config;

import lombok.Getter;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 14:13
 * @description 本地事务状态，它是一个枚举类。
 */
@Getter
public enum LocalTransactionState {
    //提交事务消息，消费者可以看到此消息
    COMMIT_MESSAGE,
    //回滚事务消息，消费者不会看到此消息
    ROLLBACK_MESSAGE,
    //事务未知状态，需要调用事务状态回查，确定此消息是提交还是回滚
    UNKNOW;
}
