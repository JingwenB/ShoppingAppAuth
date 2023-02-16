package com.beaconfire.springsecurityauth.exception;

public class SaveFailedException extends RuntimeException{


    public SaveFailedException(String message){
        super(String.format(message));

    }
}
