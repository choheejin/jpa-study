package com.ssafy.common.exception.handler;

import com.ssafy.common.exception.BusinessException;
import com.ssafy.common.model.response.ErrorResponseBody;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseBody> handleCustomException(BusinessException ex) {

        log.error("사용자 정의 에러 발생");

        final ErrorResponseBody errorResponseBody = ErrorResponseBody.of(ex.getErrorCode());

        return new ResponseEntity<>(errorResponseBody, HttpStatus.valueOf(errorResponseBody.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleServerException(Exception ex) {
        log.error("전체 에러 발생");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
