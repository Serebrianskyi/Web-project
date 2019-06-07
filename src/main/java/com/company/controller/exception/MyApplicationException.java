package com.company.controller.exception;

public class MyApplicationException extends RuntimeException {
    public MyApplicationException(String message, Throwable cause){
        super(message,cause);
    }
}
