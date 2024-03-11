package com.HopeLunchSystem.HLS.exceptions;

public class ErrorsException extends  RuntimeException{
    private final int errorCode;
    private final String errorMessage;
    public ErrorsException(String message,int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }
    public ErrorsException(String message,int errorCode,String errors) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = errors;
    }
}
