package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    public CreateResponseDto createSchedule(CreateRequestDto createRequestDto){
        Schedule schedule = new Schedule(createRequestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        CreateResponseDto createResponseDto = new CreateResponseDto(saveSchedule);
        return createResponseDto;
    }

    public ReadResponseDto readSchedule(Long scheduleId) throws NullPointerException {
        Schedule readSchedule = scheduleRepository.findById(scheduleId).orElseThrow();
        ReadResponseDto readResponseDto = new ReadResponseDto(readSchedule);

        return readResponseDto;
    }
}
