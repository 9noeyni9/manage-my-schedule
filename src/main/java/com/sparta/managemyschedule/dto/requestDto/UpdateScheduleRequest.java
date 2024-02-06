package com.sparta.managemyschedule.dto.requestDto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String content;

    public UpdateScheduleRequest(String title, String content){
        this.title = title;
        this.content =  content;
    }
}
