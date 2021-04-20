package com.suncorp.astronautinfo.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ApplicationError> handleBusinessError(BusinessException ex) {
        HttpStatus status;
        if (ex.getMessage().equalsIgnoreCase(ErrorCodes.ERROR_101)) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        ApplicationError error = new ApplicationError(String.valueOf(status), ex.getMessage());
        log.error("Error Occurred: {}", ex.getStackTrace());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({TechnicalException.class, RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseEntity<ApplicationError> handleTechError(Exception ex) {
        ApplicationError error = new ApplicationError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage());
        log.error("Error Occurred: {}", ex.getStackTrace());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ApplicationError> handleValidationError(ConstraintViolationException ex) {
        ApplicationError error = new ApplicationError(String.valueOf(HttpStatus.BAD_REQUEST), ErrorCodes.ERROR_102);
        log.error("Error Occurred: {}", ex.getStackTrace());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}




