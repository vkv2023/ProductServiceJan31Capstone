package com.example.ProductServiceJan31Capstone.exceptions;

import com.example.ProductServiceJan31Capstone.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Controller advice class dictates that RestControllerAdvice can control global exceptions

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Null pointer Exception..");
        errorDto.setStatus("Failure");
        return errorDto;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> productNotFoundException(ProductNotFoundException e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus("Failure");
        errorDto.setMessage(e.getMessage());

        ResponseEntity<ErrorDto> responseEntity =
                new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
