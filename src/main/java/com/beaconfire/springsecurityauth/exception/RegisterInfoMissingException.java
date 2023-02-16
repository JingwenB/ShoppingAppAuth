package com.beaconfire.springsecurityauth.exception;

public class RegisterInfoMissingException extends RuntimeException{


    public RegisterInfoMissingException(String message){
        super(String.format(message));

    }
}
