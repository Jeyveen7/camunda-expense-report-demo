package com.camunda.bpm.sbexpensereportdemo.exception;

public class RequesterNotFound extends RuntimeException {

    public RequesterNotFound(String message){
        super(message);
    }
}
