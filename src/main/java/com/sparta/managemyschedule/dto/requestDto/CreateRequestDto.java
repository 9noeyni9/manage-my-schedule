package com.sparta.managemyschedule.dto.requestDto;

import lombok.Getter;

@Getter
public class CreateRequestDto {
    private String title;
    private String content;
    private String manager;
    private String password;
}
