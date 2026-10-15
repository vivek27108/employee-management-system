package com.employee.employee_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//poori application ke controllers ke exceptions handle karne mein madad karta hai
public class GlobalExceptionHandler {
    // 1. Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)//kisi specific exception ko handle karte hai.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        return new ErrorResponse(message, 400);
    }
    //2. Wrong username or password
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentials(BadCredentialsException ex){
        return new ErrorResponse("Invalid username or password", 401);
    }

    //3. Employer not found
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmployeeNotFound(EmployeeNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), 404);
    }
}
