package com.beaconfire.springsecurityauth.exception;

public class RegisterEmailDuplicateException extends RuntimeException{


    public RegisterEmailDuplicateException(String message){
        super(String.format(message));

    }
}
