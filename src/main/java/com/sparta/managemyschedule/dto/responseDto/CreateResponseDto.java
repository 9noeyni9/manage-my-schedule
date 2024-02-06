package com.sparta.managemyschedule.dto.responseDto;

import com.sparta.managemyschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    
    public CreateResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate();
    }
}
