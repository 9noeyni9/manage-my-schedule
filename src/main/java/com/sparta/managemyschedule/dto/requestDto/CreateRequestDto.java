package com.sparta.managemyschedule.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRequestDto {
    private String title;
    private String content;
}
