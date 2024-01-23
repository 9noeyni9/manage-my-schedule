package com.sparta.managemyschedule.dto.responseDto;

import com.sparta.managemyschedule.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ReadResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private String manager;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    public ReadResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
    }
}
