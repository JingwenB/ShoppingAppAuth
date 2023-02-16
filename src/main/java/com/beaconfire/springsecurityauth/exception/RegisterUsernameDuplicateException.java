package com.beaconfire.springsecurityauth.exception;

public class RegisterUsernameDuplicateException extends RuntimeException{


    public RegisterUsernameDuplicateException(String message){
        super(String.format(message));

    }
}
