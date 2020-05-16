package com.dupake.order.exception;

/**
 * 用户异常捕捉类
 */
public class UserException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public UserException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public UserException() {
        super();
    }


}
