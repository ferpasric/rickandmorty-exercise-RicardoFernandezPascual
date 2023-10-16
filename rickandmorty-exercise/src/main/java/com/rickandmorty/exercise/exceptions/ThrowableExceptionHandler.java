package com.rickandmorty.exercise.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ThrowableExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleException(Throwable ex) {

        if (ex instanceof org.springframework.web.client.HttpClientErrorException) {
            logger.error(ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity<>(ExceptionUtils.getStackTrace(ex), HttpStatus.NOT_FOUND);
        } else {
            String errorMessage = HttpStatus.INTERNAL_SERVER_ERROR.name() + " " + ExceptionUtils.getStackTrace(ex);
            logger.error(errorMessage, ex);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
