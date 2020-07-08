package com.dupake.order.mapper;

import com.dupake.order.entity.TransactionLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author dupake
 * @version 1.0
 * @date 2020/4/21 14:28
 * @description
 */

public interface TransactionLogMapper {

    void insert(TransactionLog log);

    int getByTransactionId(@Param("transactionId") String transactionId);
}
