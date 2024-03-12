package com.sparta.managemyschedule.global.exception;

import com.sparta.managemyschedule.global.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException{

    private final HttpStatus status;

    public InvalidInputException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }
}
