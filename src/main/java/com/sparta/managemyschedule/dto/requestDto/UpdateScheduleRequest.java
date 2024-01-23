package com.sparta.managemyschedule.dto.requestDto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String content;
    private String manager;
    private String insertPwd;

    public UpdateScheduleRequest(String title, String content, String manager, String insertPwd){
        this.title = title;
        this.content =  content;
        this.manager = manager;
        this.insertPwd = insertPwd;
    }
}
