package com.suncorp.astronautinfo.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlerTest {

    private ErrorHandler errorHandler;

    @Before
    public void setup() {
        errorHandler = new ErrorHandler();
    }

    @Test
    public void shouldReturnBUsinessError_NotFound() {
        BusinessException exception = new BusinessException(ErrorCodes.ERROR_101);
        ResponseEntity<ApplicationError> error = errorHandler.handleBusinessError(exception);

        assertThat(error, is(notNullValue()));
        assertThat(error.getBody().getCode(), is(HttpStatus.NOT_FOUND.toString()));
    }

    @Test
    public void shouldReturnBUsinessError_BadRequest() {
        BusinessException exception = new BusinessException("Business error");
        ResponseEntity<ApplicationError> error = errorHandler.handleBusinessError(exception);

        assertThat(error, is(notNullValue()));
        assertThat(error.getBody().getCode(), is(HttpStatus.BAD_REQUEST.toString()));
    }

    @Test
    public void shouldReturnTechError() {
        TechnicalException exception = new TechnicalException("Technical error");
        ResponseEntity<ApplicationError> error = errorHandler.handleTechError(exception);

        assertThat(error, is(notNullValue()));
        assertThat(error.getBody().getCode(), is(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    }

    @Test
    public void shouldReturnValidationError() {
        Set<ConstraintViolation<?>> violations = Collections.emptySet();
        ConstraintViolationException exception = new ConstraintViolationException("", violations);
        ResponseEntity<ApplicationError> error = errorHandler.handleValidationError(exception);

        assertThat(error, is(notNullValue()));
        assertThat(error.getBody().getCode(), is(HttpStatus.BAD_REQUEST.toString()));
        assertThat(error.getBody().getMessage(), is(ErrorCodes.ERROR_102));
    }
}
