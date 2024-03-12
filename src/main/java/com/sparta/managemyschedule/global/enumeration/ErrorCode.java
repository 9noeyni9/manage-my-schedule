package com.sparta.managemyschedule.global.enumeration;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_VALID_SCHEDULE(BAD_REQUEST,"유효한 일정이 아닙니다."),
    ALREADY_EXISTS_USERNAME(BAD_REQUEST,"증복된 아이디 입니다."),
    ALREADY_EXISTS_EMAIL(BAD_REQUEST,"증복된 이메일 입니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }
}
