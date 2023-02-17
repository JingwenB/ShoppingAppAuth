package com.beaconfire.springsecurityauth.controller;

import com.beaconfire.springsecurityauth.domain.response.ErrorResponse;
import com.beaconfire.springsecurityauth.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<ErrorResponse> handleException(Exception e){
//        return new ResponseEntity<>(
//                ErrorResponse.builder()
//                        .message("Using ExceptionHandler for handling all Exception")
//                        .build(),
//                HttpStatus.INTERNAL_SERVER_ERROR // status code can be customized
//        );
//    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            SaveFailedException.class,
    })
    public ResponseEntity<ErrorResponse> handleMultipleInternalExceptions(Exception e){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .exceptionType(e.toString().split(":")[0])
                        .status(String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            RegisterInfoMissingException.class,
            RegisterUsernameDuplicateException.class,
            RegisterEmailDuplicateException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception e){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .exceptionType(e.toString().split(":")[0])
                        .status(String.valueOf( HttpStatus.BAD_REQUEST))
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            InvalidCredentialsException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(Exception e){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .exceptionType(e.toString().split(":")[0])
                        .status(String.valueOf(HttpStatus.FORBIDDEN))
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }


}
