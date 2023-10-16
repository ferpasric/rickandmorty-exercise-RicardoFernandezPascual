package com.rickandmorty.exercise.exceptions;

import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ThrowableExceptionHandlerTest {

    @Test
    void handleExceptionResourceNotFoundTest() {

        // GIVEN
        org.springframework.web.client.HttpClientErrorException httpClientErrorException = new org.springframework.web.client.HttpClientErrorException(HttpStatus.NOT_FOUND);
        ThrowableExceptionHandler throwableExceptionHandler = new ThrowableExceptionHandler();

        // WHEN
        ResponseEntity responseEntity = throwableExceptionHandler.handleException(httpClientErrorException);

        // THEN
        Assert.isTrue(HttpStatus.NOT_FOUND.name().equals(responseEntity.getStatusCode().name()), "Status Code different");
    }

    @Test
    void handleExceptionDifferentToResourceNotFoundTest() {

        // GIVEN
        RuntimeException rte = new RuntimeException("RuntimeException");
        ThrowableExceptionHandler throwableExceptionHandler = new ThrowableExceptionHandler();

        // WHEN
        ResponseEntity responseEntity = throwableExceptionHandler.handleException(rte);

        // THEN
        Assert.isTrue(HttpStatus.INTERNAL_SERVER_ERROR.name().equals(responseEntity.getStatusCode().name()), "Status Code different");
    }


}