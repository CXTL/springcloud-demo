package com.dupake.order.exception;

import com.dupake.common.message.Result;
import com.dupake.common.message.UserResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> UserException(UserException e) {
        logger.debug(UserResult.ERROR_USER_NOT_FIND.getMessage());
        Result result = new Result(UserResult.ERROR_USER_NOT_FIND.getCode(), UserResult.ERROR_USER_NOT_FIND.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
