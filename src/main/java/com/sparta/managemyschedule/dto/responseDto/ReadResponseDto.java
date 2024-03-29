package com.sparta.managemyschedule.dto.responseDto;

import com.sparta.managemyschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReadResponseDto {
    private Long scheduleId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ReadResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate();
        this.modifiedDate = schedule.getModifiedDate();
    }
}
