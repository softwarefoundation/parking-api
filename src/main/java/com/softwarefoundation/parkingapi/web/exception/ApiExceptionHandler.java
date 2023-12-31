package com.softwarefoundation.parkingapi.web.exception;

import com.softwarefoundation.parkingapi.exceptions.NegocioException;
import com.softwarefoundation.parkingapi.exceptions.UserEntityNotFoundException;
import com.softwarefoundation.parkingapi.exceptions.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request, BindingResult result) {
        log.error("methodArgumentNotValidException: ", exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválidos", result));

    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> usernameUniqueViolationException(UsernameUniqueViolationException exception, HttpServletRequest request) {
        log.error("usernameUniqueViolationException: ", exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, exception.getMessage()));

    }

    @ExceptionHandler(UserEntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> userEntityNotFoundException(UserEntityNotFoundException exception, HttpServletRequest request) {
        log.error("userEntityNotFoundException: ", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, exception.getMessage()));

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErrorMessage> negocioException(NegocioException exception, HttpServletRequest request) {
        log.error("negocioException: ", exception);
        return ResponseEntity.status(exception.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, exception.getHttpStatus(), exception.getMessage()));

    }



}
