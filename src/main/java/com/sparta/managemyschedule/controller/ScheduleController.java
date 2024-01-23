package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadAllScheduleResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponseDto createSchedule(@RequestBody CreateRequestDto createRequestDto){
        CreateResponseDto createResponseDto = scheduleService.createSchedule(createRequestDto);
        return createResponseDto;
    }

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadResponseDto readSchedule(@PathVariable Long scheduleId){
        ReadResponseDto readResponseDto = scheduleService.readSchedule(scheduleId);
        return readResponseDto;
    }

    @PutMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSchedule(@RequestBody UpdateScheduleRequest updateScheduleRequest, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(updateScheduleRequest,scheduleId);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long scheduleId){
        scheduleService.deleteSchedule(scheduleId);
    }
}
