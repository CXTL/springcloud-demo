package com.dupake.order.service.impl;

import com.dupake.order.mapper.TransactionLogMapper;
import com.dupake.order.service.TransactionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 15:07
 * @description
 */
@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    @Resource
    private TransactionLogMapper transactionLogMapper;

    @Override
    public int getByTransactionId(String transactionId) {
        return transactionLogMapper.getByTransactionId(transactionId);
    }
}
