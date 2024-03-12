package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.User;
import org.springframework.data.domain.Page;

public interface ScheduleService {

    CreateResponseDto createSchedule(User user, CreateRequestDto createRequestDto);
    ReadResponseDto readSchedule(Long scheduleId);
    Page<ReadResponseDto> readAll(User user, int page, int size, String sortBy, boolean isAsc);
    void updateSchedule(User user, UpdateScheduleRequest updateScheduleRequest, Long scheduleId);
    void deleteSchedule(User user, Long scheduleId);
}
