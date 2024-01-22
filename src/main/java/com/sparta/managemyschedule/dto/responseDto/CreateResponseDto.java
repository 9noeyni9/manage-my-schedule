package com.sparta.managemyschedule.dto.responseDto;

import com.sparta.managemyschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private String manager;
    private LocalDate createdDate;
    
    public CreateResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdDate = schedule.getCreatedDate();
    }
}
