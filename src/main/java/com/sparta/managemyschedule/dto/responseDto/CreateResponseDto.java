package com.sparta.managemyschedule.dto.responseDto;

import com.sparta.managemyschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private String manager;
    private LocalDate createdDate;
    
    // 작성일 추가

    public CreateResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.createdDate = schedule.getCreatedDate();
    }
}
